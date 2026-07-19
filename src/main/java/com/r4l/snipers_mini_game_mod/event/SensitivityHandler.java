package com.r4l.snipers_mini_game_mod.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;


public class SensitivityHandler {

    private float originalSensitivity;
    
    private boolean isSensitivityModified = false;
    
    public static float sensitivity = 0.15f;

    @SubscribeEvent
    public void onClientTick(RenderGameOverlayEvent.Pre event) {
        //if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        
        if (mc.player == null) {
            resetSensitivity();
            return;
        }

        if (mc.currentScreen != null) {
            return; 
        }

        if (ScopeHandler.isAiming && !isSensitivityModified) {
            // Grab the baseline sensitivity (which might have just been changed in the options menu)
            originalSensitivity = mc.gameSettings.mouseSensitivity;
            
            // Apply multiplier
            mc.gameSettings.mouseSensitivity = originalSensitivity * sensitivity; 
            isSensitivityModified = true;
            
        } else if (!ScopeHandler.isAiming && isSensitivityModified) {
            resetSensitivity();
        }
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        resetSensitivity();
    }

    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        resetSensitivity();
    }

    private void resetSensitivity() {
        if (isSensitivityModified) {
            Minecraft.getMinecraft().gameSettings.mouseSensitivity = originalSensitivity;
            isSensitivityModified = false;
        }
    }
}