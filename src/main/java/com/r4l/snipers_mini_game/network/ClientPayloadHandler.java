package com.r4l.snipers_mini_game.network;

import com.r4l.snipers_mini_game.Sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

public class ClientPayloadHandler {
	public static void handleHit() {
        int delayInTicks = 2;
        SimpleSoundInstance dindSound = SimpleSoundInstance.forUI(Sounds.FRF2_DIND.get(), 1.0F, 1.0F);
        //SimpleSoundInstance dindSound = SimpleSoundInstance.forUI(SoundEvents.ARROW_HIT_PLAYER, 1.0F, 1.0F);
        Minecraft.getInstance().getSoundManager().playDelayed(dindSound, delayInTicks);
    }
	
	public static void handleShoot() {
        int delayInTicks = 2;
        SimpleSoundInstance shootSound = SimpleSoundInstance.forUI(Sounds.FRF2_SHOOT.get(), 1.0F, 1.0F);
        //SimpleSoundInstance shootSound = SimpleSoundInstance.forUI(SoundEvents.FIREWORK_ROCKET_BLAST, 1.0F, 1.0F);
        Minecraft.getInstance().getSoundManager().playDelayed(shootSound, delayInTicks);
    }
}
