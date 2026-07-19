package com.r4l.snipers_mini_game_mod.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class FOVHandler {

	private float originalFOV;
	
	private boolean isFOVModified = false;

	private float modifier = 0.15f;

	@SubscribeEvent
	public void onClientTick(RenderGameOverlayEvent.Pre event) {
		//if (event.phase != TickEvent.Phase.END) return;

		Minecraft mc = Minecraft.getMinecraft();

		if (mc.player == null) {
			resetFOV();
			return;
		}

		if (mc.currentScreen != null) {
			return;
		}

		if (ScopeHandler.isAiming && !isFOVModified) {
			// Save their normal settings baseline
			originalFOV = mc.gameSettings.fovSetting;

			mc.gameSettings.fovSetting = originalFOV * modifier;

			isFOVModified = true;
		} else if (!ScopeHandler.isAiming && isFOVModified) {
			resetFOV();
		}
	}

	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		resetFOV();
	}

	@SubscribeEvent
	public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		resetFOV();
	}

	private void resetFOV() {
		if (isFOVModified) {
			Minecraft.getMinecraft().gameSettings.fovSetting = originalFOV;
			isFOVModified = false;
		}
	}
}