package com.r4l.snipers_mini_game_mod.event;
import com.r4l.snipers_mini_game_mod.item.ItemManager;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void lanternHolding(RenderLivingEvent.Pre<?> event) {
        if (!event.isCanceled() && event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            ItemStack item = player.getHeldItemMainhand(); 
            
            if (item.isEmpty()) {
                return; 
            }
            
            Item heldItem = item.getItem();
            
            if (heldItem == ItemManager.FRF2) {
                
                if (event.getRenderer() instanceof RenderPlayer) {
                    RenderPlayer rp = (RenderPlayer) event.getRenderer();
                    ModelPlayer model = rp.getMainModel();
                    
                    if (player.getPrimaryHand() == EnumHandSide.RIGHT) {
                        model.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
                    } else {
                        model.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
                }
            } 
        } 
    }
}
