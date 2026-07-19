package com.r4l.snipers_mini_game_mod.event.server;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class WeaponFiredEvent extends Event{
	
	private MessageContext ctx;
	
	private World world;
	
	public WeaponFiredEvent (MessageContext ctx, World world) {
		this.ctx = ctx;
		this.world = world;
	}

	public MessageContext getContext() {
		return ctx;
	}

	public World getWorld() {
		return world;
	}

}
