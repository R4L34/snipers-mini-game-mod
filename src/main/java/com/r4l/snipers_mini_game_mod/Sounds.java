package com.r4l.snipers_mini_game_mod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Sounds {
	
	public static SoundEvent FRF2Shoot;
	
	public static SoundEvent FRF2Reload;
	
	public static SoundEvent FRF2Dind;
	
	@SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        FRF2Shoot = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "frf2shoot")).setRegistryName("frf2shoot");
        FRF2Reload = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "frf2reload")).setRegistryName("frf2reload");
        FRF2Dind = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "csgo_dind")).setRegistryName("csgo_dind");
        event.getRegistry().register(FRF2Shoot);
        event.getRegistry().register(FRF2Reload);
        event.getRegistry().register(FRF2Dind);
    }

}
