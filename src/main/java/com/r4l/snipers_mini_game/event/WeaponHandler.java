package com.r4l.snipers_mini_game.event;

import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.event.instances.WeaponFiredEvent;
import com.r4l.snipers_mini_game.network.Payloads;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Reference.MODID)
public class WeaponHandler {

    @SubscribeEvent
    public static void onWeaponFired(WeaponFiredEvent event) {
        if (event.getLevel().isClientSide()) {
            return;
        }

        Player player = event.getPlayer();

        // Send sound to everyone tracking the player
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new Payloads.ShootSoundPayload());

        HitResult result = shootCalc(player, 150);
        
        result.breakGlass();
        result.killEntity();
        
        // result.printChat();
    }

    public static HitResult shootCalc(Player looker, double maxDistance) {
        HitResult result = new HitResult(looker, looker.level());
        
        Vec3 startVec = looker.getEyePosition(1.0F);
        Vec3 lookVec = looker.getLookAngle(); // getLook -> getLookAngle
        
        for(double distance = 0.1; distance <= maxDistance; distance += 0.1) {
            Vec3 endVec = startVec.add(lookVec.x * distance, lookVec.y * distance, lookVec.z * distance);
            
            // Entity Handler
            for (Entity target : looker.level().getEntitiesOfClass(LivingEntity.class, new AABB(startVec, endVec).inflate(1.0))) {
                if (target == looker) continue;
                
                // AxisAlignedBB -> AABB. grow() -> inflate()
                AABB entityHitbox = target.getBoundingBox().inflate(0.1);
                
                if (entityHitbox.contains(endVec)) {
                    result.setEntity(target);
                    return result;
                }
            }
            
            // Block Handler
            BlockPos pos = BlockPos.containing(endVec);
            BlockState state = looker.level().getBlockState(pos);
            VoxelShape shape = state.getCollisionShape(looker.level(), pos);
            
            if (shape.isEmpty()) {
                continue;
            }
            
            // Offset the bounding box to the block's actual position in the world
            AABB blockHitbox = shape.bounds().move(pos);
            
            if (blockHitbox.contains(endVec)) {
                // Modern check: Does this block have the Glass or Glass Panes tag?
                boolean isGlass = state.is(BlockTags.IMPERMEABLE) || state.getTags().anyMatch(tag -> tag.location().getPath().contains("glass"));
                
                if(isGlass) {
                    result.addGlass(pos);
                    continue;
                }
                
                result.addSolidBlock(pos);
            }
        }
        return result;
    }
    
    public static class HitResult {
        private final List<BlockPos> glassToBreak = new ArrayList<>();
        private final List<BlockPos> solidBlocks = new ArrayList<>();
        private Entity entityHit;
        private final Player player;
        private final Level level;
        
        public HitResult(Player player, Level level) {
            this.player = player;
            this.level = level;
        }

        public void addGlass(BlockPos pos) {
            if(!this.glassToBreak.contains(pos)) this.glassToBreak.add(pos);
        }

        public void addSolidBlock(BlockPos pos) {
            if(!this.solidBlocks.contains(pos)) this.solidBlocks.add(pos);
        }

        public void setEntity(Entity entityHit) {
            this.entityHit = entityHit;
        }
        
        public void breakGlass() {
            if(glassToBreak.isEmpty()) return;
            for(BlockPos glass : glassToBreak) {
                level.destroyBlock(glass, false);
            }
        }
        
        public void killEntity() {
            if (entityHit == null) return;
            
            float damage = 1000f;
            if (!solidBlocks.isEmpty()) {
                damage = 12f;
            }
            
            // Modern damage application
            entityHit.hurt(level.damageSources().playerAttack(player), damage);
            
            if (player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(serverPlayer, new Payloads.HitPayload());
            }
        }
        
        public void printChat() {
            for(BlockPos glass : glassToBreak) {
                player.sendSystemMessage(Component.literal("Glass: X" + glass.getX() + " Y" + glass.getY() + " Z" + glass.getZ()));
            }
            for(BlockPos solid : solidBlocks) {
                player.sendSystemMessage(Component.literal("Blocks: X" + solid.getX() + " Y" + solid.getY() + " Z" + solid.getZ()));
            }
            if(entityHit != null) {
                player.sendSystemMessage(Component.literal("Entity: X" + entityHit.getBlockX() + " Y" + entityHit.getBlockY() + " Z" + entityHit.getBlockZ()));
            }
        }
    }
}