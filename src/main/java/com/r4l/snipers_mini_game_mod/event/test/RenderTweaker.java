package com.r4l.snipers_mini_game_mod.event.test;

import com.r4l.snipers_mini_game_mod.event.SensitivityHandler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderTweaker {

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        String message = event.getMessage();

        if (message == null || message.trim().isEmpty()) {
            return;
        }

        String[] parts = message.trim().split("\\s+");
        String command = parts[0].toUpperCase();

        try {
            if (command.equals("SENSITIVITY") && parts.length >= 2) {
                // Assign to variables
                SensitivityHandler.sensitivity = Float.parseFloat(parts[1]);
                Minecraft.getMinecraft().player.sendChatMessage("Your ensitivity has been changed to " + parts[1]);
                event.setCanceled(true);
            }
        } catch (NumberFormatException e) {
        	
        }
    }
}