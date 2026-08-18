package com.r4l.snipers_mini_game.event;

import com.mojang.brigadier.CommandDispatcher;
import com.r4l.snipers_mini_game.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class CommandHandler {

	
	//@EventBusSubscriber(modid = Reference.MODID)
	public class Server{
		
		
	}
	
	
	@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
	public class Client{
	
		@SubscribeEvent
		public static void onRegisterCommands(RegisterCommandsEvent event) {
	        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

	        dispatcher.register(Commands.literal("#sensitivity").then(Commands.argument("modifier", DoubleArgumentType.doubleArg(0.1))
	        		.executes(Client::sensitivityCommand)));
	        
	        dispatcher.register(Commands.literal("#recoil").then(Commands.argument("ticks", IntegerArgumentType.integer(10, 100))
	        		.executes(Client::recoilCommand)));
	        
	        dispatcher.register(Commands.literal("#zoom").then(Commands.argument("modifier", FloatArgumentType.floatArg(0.1f, 0.9f))
	        		.executes(Client::zoomCommand)));
	        
		}
		
		
		
		private static int sensitivityCommand(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
	        
			double new_sensitivity = DoubleArgumentType.getDouble(context, "modifier");
			SensitivityHandler.sensitivityMultiplier = new_sensitivity;
			
			Minecraft.getInstance().player.sendSystemMessage(Component.literal("Your sensitivity has been changed to " + new_sensitivity + "."));;;
			
	        return 1; 
	    }
		
		private static int recoilCommand(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
	        
			int new_recoil = IntegerArgumentType.getInteger(context, "ticks");
			FireHandler.RECOIL_VALUE = new_recoil;
			
			Minecraft.getInstance().player.sendSystemMessage(Component.literal("Your Sniper Rifle recoil has been changed to " + new_recoil + "."));;;
			
	        return 1; 
	    }
		
		
		private static int zoomCommand(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
	        
			float new_zoom = FloatArgumentType.getFloat(context, "modifier");
			FOVHandler.MODIFIER = new_zoom;
			
			Minecraft.getInstance().player.sendSystemMessage(Component.literal("Your Sniper Rifle zoom has been changed to " + new_zoom + "."));;;
			
	        return 1; 
	    }
		
	}
	
	
	
}
