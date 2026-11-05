package com.r4l.snipers_mini_game.network.payloads;

import com.r4l.snipers_mini_game.Reference;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class StandardPayloads {
    
    // Fire Payload
    public record FirePayload() implements CustomPacketPayload {
        public static final Type<FirePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "fire"));
        public static final StreamCodec<ByteBuf, FirePayload> CODEC = StreamCodec.unit(new FirePayload());
        @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
    }

    // Hit Payload (For your hit marker/sound)
    public record HitPayload() implements CustomPacketPayload {
        public static final Type<HitPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "hit"));
        public static final StreamCodec<ByteBuf, HitPayload> CODEC = StreamCodec.unit(new HitPayload());
        @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
    }

    // Shoot Sound Payload
    public record ShootSoundPayload() implements CustomPacketPayload {
        public static final Type<ShootSoundPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "shoot_sound"));
        public static final StreamCodec<ByteBuf, ShootSoundPayload> CODEC = StreamCodec.unit(new ShootSoundPayload());
        @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
    }
    
    
    
    
    
}