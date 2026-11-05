package com.r4l.snipers_mini_game.event;

import com.r4l.snipers_mini_game.Reference;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class SensitivityHandler {

    private static double originalSensitivity;
    private static boolean isSensitivityModified = false;
    
    public static double sensitivityMultiplier = 0.15;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        
        if (mc.player == null) {
            resetSensitivity();
            return;
        }

        // currentScreen is now just screen
        if (mc.screen != null) {
            resetSensitivity();
            return; 
        }

        if (ScopeHandler.isAiming && !isSensitivityModified) {
            // Grab the baseline sensitivity using the OptionInstance getter
            originalSensitivity = mc.options.sensitivity().get();
            
            // Apply multiplier using the setter
            mc.options.sensitivity().set(originalSensitivity * sensitivityMultiplier); 
            isSensitivityModified = true;
            
        } else if (!ScopeHandler.isAiming && isSensitivityModified) {
            resetSensitivity();
        }
    }

    // GuiOpenEvent is now ScreenEvent.Opening
    @SubscribeEvent
    public static void onScreenOpen(ScreenEvent.Opening event) {
        resetSensitivity();
    }

    // FMLNetworkEvent is completely gone, replaced by ClientPlayerNetworkEvent
    @SubscribeEvent
    public static void onClientDisconnect(ClientPlayerNetworkEvent.LoggingOut event) {
        resetSensitivity();
    }

    private static void resetSensitivity() {
        if (isSensitivityModified) {
            Minecraft.getInstance().options.sensitivity().set(originalSensitivity);
            isSensitivityModified = false;
        }
    }
}