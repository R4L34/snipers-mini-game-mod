package com.r4l.snipers_mini_game.network.client;

import com.r4l.snipers_mini_game.weapon.WeaponSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;

public class StandartClientPH {
	public static void handleHit() {
        int delayInTicks = 2;
        SimpleSoundInstance dindSound = SimpleSoundInstance.forUI(WeaponSounds.FRF2_DIND.get(), 1.0F, 1.0F);
        Minecraft.getInstance().getSoundManager().playDelayed(dindSound, delayInTicks);
    }
	
	public static void handleShoot() {
        int delayInTicks = 2;
        SimpleSoundInstance shootSound = SimpleSoundInstance.forUI(WeaponSounds.FRF2_SHOOT.get(), 1.0F, 1.0F);
        Minecraft.getInstance().getSoundManager().playDelayed(shootSound, delayInTicks);
    }
	
	
	
}
