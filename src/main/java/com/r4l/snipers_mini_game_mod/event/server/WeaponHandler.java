package com.r4l.snipers_mini_game_mod.event.server;

import java.util.ArrayList;
import java.util.List;

import com.r4l.snipers_mini_game_mod.network.NetworkHandler;
import com.r4l.snipers_mini_game_mod.network.message.HitMessage;
import com.r4l.snipers_mini_game_mod.network.message.ShootSoundMessage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WeaponHandler {

	@SubscribeEvent
	public void onWeaponFired(WeaponFiredEvent event) {
		
		if(event.getWorld().isRemote) {
			return;
		}

		EntityPlayerMP player = event.getContext().getServerHandler().player;

		//event.getContext().getServerHandler().player.world.playSound(null, player.posX, player.posY, player.posZ, Sounds.FRF2Shoot, SoundCategory.HOSTILE, 1.0F ,1.0F);
		NetworkHandler.channel.sendToAll(new ShootSoundMessage());
		
		for (Entity target : player.world.loadedEntityList) {
			
			if (player.equals(target)) {
				continue;
			}
			
			if (!(target instanceof EntityLivingBase)) {
                continue;
            }

			EntityLookResult result = isLookingAtEntity(player, target, 500f);

			if (result.isLookingAtEntity()) {
				
				// remove air, stairs, and signs
				result.blocksInPath.removeIf(pos -> {
				    IBlockState state = player.world.getBlockState(pos);
				    Block block = state.getBlock();
				    
				    return block.isAir(state, player.world, pos) || 
				           block instanceof BlockLadder || 
				           block instanceof BlockSign;
				});
				
				
				float damage = 1000f;
				
				if(result.blocksInPath.size() == 1) {
					damage = 14f;
				}
				
				else if(result.blocksInPath.size() > 1) {
					damage = 7f;
				}
				
				//Do the shot
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
				NetworkHandler.channel.sendTo(new HitMessage(), player);
			}
		}

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
	
	
	public static EntityLookResult isLookingAtEntity(EntityPlayer looker, Entity target, double maxDistance) {
        Vec3d startVec = looker.getPositionEyes(1.0F);
        Vec3d lookVec = looker.getLook(1.0F);
        Vec3d endVec = startVec.addVector(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);

        AxisAlignedBB paddedBB = target.getEntityBoundingBox();
        RayTraceResult result = paddedBB.calculateIntercept(startVec, endVec);

        List<BlockPos> blocks = new ArrayList<>();

        if (result != null) {
            // Get the distance from the player's eyes to the exact impact point on the entity
            double distanceToHit = startVec.distanceTo(result.hitVec);
            
            // Step size (0.1 blocks). Smaller = more precise but heavier. 0.1 is very safe.
            double stepSize = 0.1D; 
            BlockPos lastPos = null;

            // Step forward along the look vector and record each unique BlockPos
            for (double d = 0; d <= distanceToHit; d += stepSize) {
                Vec3d currentPosVec = startVec.addVector(lookVec.x * d, lookVec.y * d, lookVec.z * d);
                BlockPos currentPos = new BlockPos(currentPosVec);

                if (!currentPos.equals(lastPos)) {
                    blocks.add(currentPos);
                    lastPos = currentPos;
                }
            }

            // Ensure the exact block containing the final impact coordinate is included
            BlockPos finalHitPos = new BlockPos(result.hitVec);
            if (!finalHitPos.equals(lastPos)) {
                blocks.add(finalHitPos);
            }
        }

        return new EntityLookResult(result, blocks);
    }
	
	public static class EntityLookResult {
        public final RayTraceResult hitResult;
        public final List<BlockPos> blocksInPath;

        public EntityLookResult(RayTraceResult hitResult, List<BlockPos> blocksInPath) {
            this.hitResult = hitResult;
            this.blocksInPath = blocksInPath;
        }

        public boolean isLookingAtEntity() {
            return hitResult != null;
        }
    }
}

	
