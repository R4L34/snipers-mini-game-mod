package com.r4l.snipers_mini_game_mod.network.message;


import com.r4l.snipers_mini_game_mod.Sounds;
import com.r4l.snipers_mini_game_mod.network.NetworkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ShootSoundMessage implements IMessage{
	
	public ShootSoundMessage() {}


	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}
	
	
	
	public static class Handler implements IMessageHandler<ShootSoundMessage, IMessage> {
		 @Override
		    public IMessage onMessage(ShootSoundMessage message, MessageContext ctx) {
		    	NetworkHandler.getThreadListener(ctx).addScheduledTask(() -> {
		    		//Minecraft.getMinecraft().player.playSound(Sounds.FRF2Dind, 1.0f, 1.0f);
			    	//int delayInTicks = 0; 
			    	PositionedSoundRecord hitSound = PositionedSoundRecord.getMasterRecord(Sounds.FRF2Shoot, 1.0F);
			    	Minecraft.getMinecraft().getSoundHandler().playSound(hitSound);
		    	});
				return null;
		 }
	}
	
}
