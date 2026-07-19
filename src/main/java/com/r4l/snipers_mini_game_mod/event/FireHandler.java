package com.r4l.snipers_mini_game_mod.event;

import com.r4l.snipers_mini_game_mod.item.ItemManager;
import com.r4l.snipers_mini_game_mod.network.NetworkHandler;
import com.r4l.snipers_mini_game_mod.network.message.FireMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FireHandler {
	
	public static int RECOIL = 0;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (RECOIL > 0 && event.phase == TickEvent.Phase.END) {
			RECOIL--;
		}
	}

	@SubscribeEvent
	public void onFireInput(MouseEvent event) {
		if (event.getButton() == 0 && event.isButtonstate() && Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemManager.FRF2 && RECOIL == 0) {
			RECOIL = 30;
			NetworkHandler.channel.sendToServer(new FireMessage());
		}
	}
}
