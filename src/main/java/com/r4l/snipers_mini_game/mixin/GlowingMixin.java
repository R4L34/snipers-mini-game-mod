package com.r4l.snipers_mini_game.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.r4l.snipers_mini_game.SMGData;
import com.r4l.snipers_mini_game.team.Team;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.world.entity.Entity;

@Mixin(Minecraft.class)
public class GlowingMixin {
	@Inject(method = "shouldEntityAppearGlowing", at = @At("HEAD"), cancellable = true)
	private void makeOtherPlayersGlow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		SMGData data = SMGData.getInstance();

		if (entity instanceof RemotePlayer otherPlayer) {
			if (Team.isOnTheSameTeam(data.getTeams() ,Minecraft.getInstance().player, otherPlayer)) {
				//if (data.isTeamWallHack()) {
					cir.setReturnValue(true);
				//}
			}
		}
		
		//cir.setReturnValue(true);
	}
}
