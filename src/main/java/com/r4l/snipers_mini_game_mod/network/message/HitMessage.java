package com.r4l.snipers_mini_game_mod.network.message;

import com.r4l.snipers_mini_game_mod.Sounds;
import com.r4l.snipers_mini_game_mod.network.NetworkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HitMessage implements IMessage{
	
	public HitMessage() {}


	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}
	
	
	
	public static class Handler implements IMessageHandler<HitMessage, IMessage> {
		 @Override
		    public IMessage onMessage(HitMessage message, MessageContext ctx) {
		    	NetworkHandler.getThreadListener(ctx).addScheduledTask(() -> {
		    		//Minecraft.getMinecraft().player.playSound(Sounds.FRF2Dind, 1.0f, 1.0f);
			    	int delayInTicks = 2; 
			    	PositionedSoundRecord hitSound = PositionedSoundRecord.getMasterRecord(Sounds.FRF2Dind, 1.0F);
			    	Minecraft.getMinecraft().getSoundHandler().playDelayedSound(hitSound, delayInTicks);
		    	});
				return null;
		 }
	}
	
}
