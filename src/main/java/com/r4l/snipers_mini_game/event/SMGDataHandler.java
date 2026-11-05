package com.r4l.snipers_mini_game.event;

import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.SMGData;
import com.r4l.snipers_mini_game.network.payloads.TeamPayloads;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.network.PacketDistributor;


public class SMGDataHandler {

	@EventBusSubscriber(modid = Reference.MODID)
	public static class Server {
		@SubscribeEvent
		public static void login(PlayerLoggedInEvent event) {
			ServerPlayer player = (ServerPlayer) event.getEntity();
			PacketDistributor.sendToPlayer(player, new TeamPayloads.SyncTeams(SMGData.getInstance().getTeams()));
		}

	}

	@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
	public static class Clisnt {
		@SubscribeEvent
		public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
			SMGData.getInstance().emptyTeams();
		}
	}

}
