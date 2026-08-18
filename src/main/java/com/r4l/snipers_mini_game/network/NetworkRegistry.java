package com.r4l.snipers_mini_game.network;

import com.r4l.snipers_mini_game.Reference;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Reference.MODID)
public class NetworkRegistry {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Reference.MODID);

        // Server receives FirePayload from Client
        registrar.playToServer(Payloads.FirePayload.TYPE, Payloads.FirePayload.CODEC, ServerPayloadHandler::handleFire);

        // Client receives these from Server
        registrar.playToClient(Payloads.HitPayload.TYPE, Payloads.HitPayload.CODEC,(payload, context) -> context.enqueueWork(ClientPayloadHandler::handleHit));
        
        registrar.playToClient(Payloads.ShootSoundPayload.TYPE, Payloads.ShootSoundPayload.CODEC, (payload, context) -> context.enqueueWork(ClientPayloadHandler::handleShoot));;
    }
}