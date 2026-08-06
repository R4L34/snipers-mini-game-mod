package com.r4l.snipers_mini_game_mod.event.server;

import java.util.ArrayList;
import java.util.List;

import com.r4l.snipers_mini_game_mod.network.NetworkHandler;
import com.r4l.snipers_mini_game_mod.network.message.HitMessage;
import com.r4l.snipers_mini_game_mod.network.message.ShootSoundMessage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WeaponHandler {

	@SubscribeEvent
	public void onWeaponFired(WeaponFiredEvent event) {
		
		if(event.getWorld().isRemote) {
			return;
		}

		EntityPlayerMP player = event.getContext().getServerHandler().player;

		NetworkHandler.channel.sendToAll(new ShootSoundMessage());

		HitResult result = shootCalc(player, 150);
		
		result.breakGlass();
		result.killEntity();
		
		//result.printChat();
	}

	/*public static RayTraceResult isLookingAtEntity(EntityPlayer looker, Entity target, double maxDistance) {
		Vec3d startVec = looker.getPositionEyes(1.0F);
		Vec3d lookVec = looker.getLook(1.0F);
		Vec3d endVec = startVec.addVector(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);

		AxisAlignedBB paddedBB = target.getEntityBoundingBox().grow(0.1D);

		RayTraceResult result = paddedBB.calculateIntercept(startVec, endVec);

		//return result != null;
		return result;
	}*/

	public static HitResult shootCalc(EntityPlayer looker, double maxDistance) {
		HitResult result = new HitResult(looker, looker.world);
		
		Vec3d startVec = looker.getPositionEyes(1.0F);
		Vec3d lookVec = looker.getLook(1.0F);
		
		for(double distance = 0.1; distance <= maxDistance; distance += 0.1) {
			//our current step
			Vec3d endVec = startVec.addVector(lookVec.x * distance, lookVec.y * distance, lookVec.z * distance);
			
			//Entity Handler. If found - return the function
			for (Entity target : looker.world.loadedEntityList) {
				
				if (target == looker) {
	                continue;
	            }
				
				if (!(target instanceof EntityLivingBase)) {
	                continue;
	            }
				
		        AxisAlignedBB entityHitbox = target.getEntityBoundingBox().grow(0.1);
		        boolean hitResult = entityHitbox.contains(endVec);
		        
		        //Shot em
		        if (hitResult) {
		        	result.setEntity(target);
		        	return result;
		        }
			}
			
			//Entity on this step not found. Lets move on to blocks
			
			BlockPos pos = new BlockPos(endVec);
			IBlockState state = looker.world.getBlockState(pos);
			AxisAlignedBB hitbox = state.getCollisionBoundingBox(looker.world, pos);
			
			//no block here
			if (hitbox == null) {
				continue;
			}
			
			AxisAlignedBB blockHitbox = hitbox.offset(pos);
			
			boolean hitResult = blockHitbox.contains(endVec);
			
			if (hitResult) {
				Block block = state.getBlock();
				boolean isGlass = block instanceof BlockGlass || block instanceof BlockStainedGlass ||  (block instanceof BlockPane && block.getDefaultState().getMaterial() == Material.GLASS);
				if(isGlass) {
					result.addGlass(pos);
					continue;
				}
				
				result.addSolidBlock(pos);
	        }
			
		}
		return result;
	}
	
	
	public static class HitResult{
		
		private List<BlockPos> glassToBreak;
		
		private List<BlockPos> solidBlocks;
		
		private Entity entityHit;
		
		private EntityPlayer player;
		
		private World world;
		
		public HitResult(EntityPlayer player, World world) {
			glassToBreak = new ArrayList<>();
			solidBlocks = new ArrayList<>();
			this.player = player;
			this.world = world;
		}

		public void addGlass(BlockPos glassToBreak) {
			if(this.glassToBreak.contains(glassToBreak)) return;
			this.glassToBreak.add(glassToBreak);
		}


		public void addSolidBlock(BlockPos block) {
			if(this.solidBlocks.contains(block)) return;
			this.solidBlocks.add(block);
		}

		public Entity getEntity() {
			return entityHit;
		}

		public void setEntity(Entity entityHit) {
			this.entityHit = entityHit;
		}
		
		public void breakGlass() {
			if(glassToBreak.isEmpty()) { return; }
			for(BlockPos glass : glassToBreak) {
				world.destroyBlock(glass, false);
			}
		}
		
		public void killEntity() {
			if (entityHit == null) return;
			
			float damage = 1000f;
			
			if (solidBlocks.size() > 0) {
				damage = 12;
			}
			
			entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
			NetworkHandler.channel.sendTo(new HitMessage(), (EntityPlayerMP) player);
		}
		
		public void printChat() {
			for(BlockPos glass : glassToBreak) {
				player.sendMessage(new TextComponentString("Glass:"));
				player.sendMessage(new TextComponentString("X" + Integer.toString(glass.getX())));
				player.sendMessage(new TextComponentString("Y" + Integer.toString(glass.getY())));
				player.sendMessage(new TextComponentString("Z" + Integer.toString(glass.getZ())));
			}
			
			player.sendMessage(new TextComponentString(" "));
			player.sendMessage(new TextComponentString(" "));
			
			for(BlockPos glass : solidBlocks) {
				player.sendMessage(new TextComponentString("Blocks:"));
				player.sendMessage(new TextComponentString("X" + Integer.toString(glass.getX())));
				player.sendMessage(new TextComponentString("Y" + Integer.toString(glass.getY())));
				player.sendMessage(new TextComponentString("Z" + Integer.toString(glass.getZ())));
			}
			
			if(entityHit != null) {
				player.sendMessage(new TextComponentString("Entity:"));
				player.sendMessage(new TextComponentString("X" + Integer.toString(entityHit.getPosition().getX())));
				player.sendMessage(new TextComponentString("Y" + Integer.toString(entityHit.getPosition().getY())));
				player.sendMessage(new TextComponentString("Z" + Integer.toString(entityHit.getPosition().getZ())));
			}
	
		}

	}

}

	
