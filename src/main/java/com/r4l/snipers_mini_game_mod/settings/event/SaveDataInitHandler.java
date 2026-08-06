package com.r4l.snipers_mini_game_mod.settings.event;

import com.r4l.snipers_mini_game_mod.settings.Engine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SaveDataInitHandler {
	public class SyncHandler {
		
		@SubscribeEvent
		public void onJoinWorld(EntityJoinWorldEvent event) {
			Entity entity = event.getEntity();
			if (entity.world.isRemote || !(entity instanceof EntityPlayer))
				return;
			EntityPlayer player = (EntityPlayer) entity;

			Engine engine = Engine.getInstance(player.world);
			engine.syncAndSave();

		}
	}
}
