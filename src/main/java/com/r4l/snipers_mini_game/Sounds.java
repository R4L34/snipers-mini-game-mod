package com.r4l.snipers_mini_game;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Reference.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> FRF2_SHOOT = registerSound("frf2shoot");
    public static final DeferredHolder<SoundEvent, SoundEvent> FRF2_RELOAD = registerSound("frf2reload");
    public static final DeferredHolder<SoundEvent, SoundEvent> FRF2_DIND = registerSound("csgo_dind");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Reference.MODID, name)));
    }
    
}
