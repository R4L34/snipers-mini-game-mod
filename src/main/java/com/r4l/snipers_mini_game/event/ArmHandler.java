package com.r4l.snipers_mini_game.event;

import com.r4l.snipers_mini_game.Items;
import com.r4l.snipers_mini_game.Reference;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class ArmHandler {

    @SubscribeEvent
    public static void lanternHolding(RenderLivingEvent.Pre<?, ?> event) {
    	
        if (event.getEntity() instanceof Player player) {

            ItemStack mainHandItem = player.getMainHandItem(); 
            
            if (mainHandItem.isEmpty()) {
                return; 
            }
           
            if (mainHandItem.getItem() == Items.FRF2.get()) {
                if (event.getRenderer().getModel() instanceof HumanoidModel<?> model) {
                    
                    if (player.getMainArm() == HumanoidArm.RIGHT) {
                        model.rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
                    } else {
                        model.leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
                    }
                }
            } 
        } 
    }
}