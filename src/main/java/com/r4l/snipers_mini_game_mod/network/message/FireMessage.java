package com.r4l.snipers_mini_game_mod.network.message;

import com.r4l.snipers_mini_game_mod.event.server.WeaponFiredEvent;
import com.r4l.snipers_mini_game_mod.network.NetworkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class FireMessage implements IMessage{
	
	public FireMessage() {}


	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}
	
	
	
	public static class Handler implements IMessageHandler<FireMessage, IMessage> {
		 @Override
		    public IMessage onMessage(FireMessage message, MessageContext ctx) {
		    	NetworkHandler.getThreadListener(ctx).addScheduledTask(() -> {
		    		MinecraftForge.EVENT_BUS.post(new WeaponFiredEvent(ctx, ctx.getServerHandler().player.world));
		    	});
				return null;
		 }
	}
	
}
