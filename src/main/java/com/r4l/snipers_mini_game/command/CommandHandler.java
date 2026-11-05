package com.r4l.snipers_mini_game.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.command.commands.TeamCommand;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class CommandHandler {

	@EventBusSubscriber(modid = Reference.MODID)
	public static class Server {

		@SubscribeEvent
		public static void onRegisterCommands(RegisterCommandsEvent event) {
			CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

	        dispatcher.register(Commands.literal("#team")
	            .then(Commands.literal("create")
	                .then(Commands.argument("NAME", StringArgumentType.word())
	                    .executes(TeamCommand::create)))
	            
	            .then(Commands.literal("destroy")
	                .then(Commands.argument("NAME", StringArgumentType.word())
	                    .executes(TeamCommand::destroy)))
	            
	            .then(Commands.literal("destroyall")
	                .executes(TeamCommand::destroyAll))
	            
	            .then(Commands.literal("add")
	                .then(Commands.argument("NAME", StringArgumentType.word())
	                    .then(Commands.argument("PLAYERS", EntityArgument.players())
	                        .executes(TeamCommand::add))))
	            
	            .then(Commands.literal("remove")
	                .then(Commands.argument("NAME", StringArgumentType.word())
	                    .then(Commands.argument("PLAYERS", EntityArgument.players())
	                        .executes(TeamCommand::remove))))
	            
	            .then(Commands.literal("clean")
	                .then(Commands.argument("NAME", StringArgumentType.word())
	                    .executes(TeamCommand::clean)))
	            
	            .then(Commands.literal("print")
	                .executes(TeamCommand::print1) // Execution for empty optional (/#team print)
	                .then(Commands.argument("NAME", StringArgumentType.word())
	                    .executes(TeamCommand::print2))) // Execution with argument (/#team print <NAME>)
	        );


	        dispatcher.register(Commands.literal("#start")
	            .executes(CommandHandler::NYI)
	            .then(Commands.argument("PLAYERS", EntityArgument.players())
	            		.executes(CommandHandler::NYI))
	        );


	        dispatcher.register(Commands.literal("#spectate")
	            .executes(CommandHandler::NYI)
	            .then(Commands.argument("PLAYERS", EntityArgument.players())
	            		.executes(CommandHandler::NYI))
	            );


	        dispatcher.register(Commands.literal("#reloadTime")
	            .then(Commands.argument("TICKS", IntegerArgumentType.integer())
	                .executes(CommandHandler::NYI))
	        );
	    
		}
		
		

	}

	@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
	public static class Client {

		@SubscribeEvent
		public static void onRegisterCommands(RegisterClientCommandsEvent event) {
			CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();


	        dispatcher.register(Commands.literal("#pov")
	            .then(Commands.literal("leftHand")
	                .executes(CommandHandler::NYI))
	            .then(Commands.literal("rightHand")
	                .executes(CommandHandler::NYI))
	        );


	        dispatcher.register(Commands.literal("#sensitivity")
	            .then(Commands.argument("value", FloatArgumentType.floatArg())
	                .executes(CommandHandler::NYI))
	        );
	    
		}

	}
	
	public static int NYI(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		
		ServerPlayer player = ctx.getSource().getPlayerOrException();
	    player.sendSystemMessage(Component.literal("This Command is not yet implemented!"));
		return 1;
	}

}
