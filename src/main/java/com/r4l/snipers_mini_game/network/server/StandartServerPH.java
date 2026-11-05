package com.r4l.snipers_mini_game.network.server;

import com.r4l.snipers_mini_game.event.instances.WeaponFiredEvent;
import com.r4l.snipers_mini_game.network.payloads.StandardPayloads;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class StandartServerPH {

	 public static void handleFire(final StandardPayloads.FirePayload payload, final IPayloadContext context) {
	        context.enqueueWork(() -> {
	            if (context.player() != null) {
	                NeoForge.EVENT_BUS.post(new WeaponFiredEvent(context.player(), context.player().level()));
	            }
	        });
	    }
	
}
