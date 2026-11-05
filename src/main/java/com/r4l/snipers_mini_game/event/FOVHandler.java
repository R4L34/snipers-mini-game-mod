package com.r4l.snipers_mini_game.event;

import com.r4l.snipers_mini_game.Reference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class FOVHandler {

    public static float MODIFIER = 0.15f;

    @SubscribeEvent
    public static void onFov(ComputeFovModifierEvent event) {
        if (ScopeHandler.isAiming) {
            event.setNewFovModifier(MODIFIER);
        }
    }
}