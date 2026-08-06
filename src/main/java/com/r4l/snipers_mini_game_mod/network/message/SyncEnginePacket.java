package com.r4l.snipers_mini_game_mod.network.message;

import com.r4l.snipers_mini_game_mod.settings.Engine;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncEnginePacket implements IMessage {
    
    private NBTTagCompound data;

    public SyncEnginePacket() {
    	
    }

    public SyncEnginePacket(NBTTagCompound data) {
        this.data = data;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.data);
    }

    public static class Handler implements IMessageHandler<SyncEnginePacket, IMessage> {
        @Override
        public IMessage onMessage(SyncEnginePacket message, MessageContext ctx) {
            // Ensure client-side thread safety
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (Minecraft.getMinecraft().world != null) {
                    // Update the client's version of Engine
                    Engine clientEngine = Engine.getInstance(Minecraft.getMinecraft().world);
                    clientEngine.updateFromPacket(message.data);
                }
            });
            return null; 
        }
    }
}