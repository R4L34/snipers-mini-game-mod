package com.r4l.snipers_mini_game.event;

import com.r4l.snipers_mini_game.Items;
import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.network.Payloads;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class FireHandler {
    
    public static int RECOIL = 0;
    
    public static int RECOIL_VALUE = 50;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (RECOIL > 0) {
            RECOIL--;
        }
    }

    @SubscribeEvent
    public static void onAttackTriggered(InputEvent.InteractionKeyMappingTriggered event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // isAttack() checks if this is specifically the Left-Click/Attack action
        if (event.isAttack() && mc.player.getMainHandItem().is(Items.FRF2.get())) {
            
            // 1. Completely cancels the vanilla attack logic
            // (This prevents the Attack Cooldown timer from resetting, fixing the dip!)
            event.setCanceled(true); 
            
            // 2. Suppresses the vanilla arm swing
            event.setSwingHand(false); 
            
            // 3. Fire the weapon
            if (RECOIL == 0) {
                RECOIL = RECOIL_VALUE;
                PacketDistributor.sendToServer(new Payloads.FirePayload());
            }
        }
    }
}
