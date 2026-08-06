package com.r4l.snipers_mini_game_mod.network;

import com.r4l.snipers_mini_game_mod.network.message.FireMessage;
import com.r4l.snipers_mini_game_mod.network.message.HitMessage;
import com.r4l.snipers_mini_game_mod.network.message.ShootSoundMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkHandler {

	public static SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel("smg_mod_network");

	public static void init() {
		int i = 0;
		channel.registerMessage(FireMessage.Handler.class, FireMessage.class, i++, Side.SERVER);
		channel.registerMessage(HitMessage.Handler.class, HitMessage.class, i++, Side.CLIENT);
		channel.registerMessage(ShootSoundMessage.Handler.class, ShootSoundMessage.class, i++, Side.CLIENT);
		channel.registerMessage(ShootSoundMessage.Handler.class, ShootSoundMessage.class, i++, Side.CLIENT);
	}

	public static IThreadListener getThreadListener(MessageContext ctx) {
		return ctx.side == Side.SERVER ? (WorldServer) ctx.getServerHandler().player.world : getClientThreadListener();
	}

	@SideOnly(Side.CLIENT)
	public static IThreadListener getClientThreadListener() {
		return Minecraft.getMinecraft();
	}
}
