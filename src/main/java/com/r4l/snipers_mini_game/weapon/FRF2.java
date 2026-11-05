package com.r4l.snipers_mini_game.weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FRF2 extends Item {

    public FRF2(Item.Properties properties) {
        super(properties.stacksTo(1).attributes(createAttributes()));
    }

    private static ItemAttributeModifiers createAttributes() {
    	
        return ItemAttributeModifiers.builder().build();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.literal("Weapon: FR F2"));
        tooltip.add(Component.literal("Rate of Fire: Bolt Action"));
        tooltip.add(Component.literal("Type: Sniper Rifle"));
        tooltip.add(Component.literal("Model: Vroulas"));
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 0.0F;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving) {
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }
    
    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false; 
    }
    
    
    
}