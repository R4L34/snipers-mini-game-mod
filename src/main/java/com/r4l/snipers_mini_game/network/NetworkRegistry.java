package com.r4l.snipers_mini_game.network;

import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.network.client.StandartClientPH;
import com.r4l.snipers_mini_game.network.client.TeamClientPH;
import com.r4l.snipers_mini_game.network.payloads.StandardPayloads;
import com.r4l.snipers_mini_game.network.payloads.TeamPayloads;
import com.r4l.snipers_mini_game.network.server.StandartServerPH;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Reference.MODID)
public class NetworkRegistry {

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
	    final PayloadRegistrar registrar = event.registrar(Reference.MODID);

	    // Standart
	    registrar.playToServer(StandardPayloads.FirePayload.TYPE, StandardPayloads.FirePayload.CODEC, 
	    		(payload, context) -> context.enqueueWork(() -> StandartServerPH.handleFire(payload, context)));
	    registrar.playToClient(StandardPayloads.HitPayload.TYPE, StandardPayloads.HitPayload.CODEC,
	            (payload, context) -> context.enqueueWork(() -> StandartClientPH.handleHit()));
	    registrar.playToClient(StandardPayloads.ShootSoundPayload.TYPE, StandardPayloads.ShootSoundPayload.CODEC, 
	            (payload, context) -> context.enqueueWork(() -> StandartClientPH.handleShoot()));
	    
	    //Team
	    registrar.playToClient(TeamPayloads.SyncTeams.TYPE, TeamPayloads.SyncTeams.CODEC,
	    		(payload, context) -> context.enqueueWork(() -> TeamClientPH.handleSyncMultiple(payload, context)));
	    
	    
	    
	}
}