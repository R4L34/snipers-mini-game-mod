package com.r4l.snipers_mini_game.network;

import com.r4l.snipers_mini_game.event.instances.WeaponFiredEvent;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {

	 public static void handleFire(final Payloads.FirePayload payload, final IPayloadContext context) {
	        context.enqueueWork(() -> {
	            // Safely execute on the server thread
	            if (context.player() != null) {
	                NeoForge.EVENT_BUS.post(new WeaponFiredEvent(context.player(), context.player().level()));
	            }
	        });
	    }
	
}
