package com.r4l.snipers_mini_game.command.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.r4l.snipers_mini_game.SMGData;
import com.r4l.snipers_mini_game.network.payloads.TeamPayloads;
import com.r4l.snipers_mini_game.team.Team;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class TeamCommand {

	public static int create(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

		String name = StringArgumentType.getString(ctx, "NAME");
		Team team = new Team(name);
		// TODO optimize later
		List<Team> teams = SMGData.getInstance().getTeams();
		if (teams.contains(team)) {
			print("The server already has a team with that name!", ctx);
			return 1;

		}

		teams.add(team);
		PacketDistributor.sendToAllPlayers(new TeamPayloads.SyncTeams(teams));

		print("Successfully created team " + team.getTeamName(), ctx);

		return 1;
	}

	public static int destroy(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		String name = StringArgumentType.getString(ctx, "NAME");
		Team team = new Team(name);
		// TODO optimize later
		List<Team> teams = SMGData.getInstance().getTeams();
		if (!teams.contains(team)) {
			print("The server does not have a team with that name!", ctx);
			return 1;
		}
		teams.remove(team);
		PacketDistributor.sendToAllPlayers(new TeamPayloads.SyncTeams(teams));
		print("Successfully destroyed team " + team.getTeamName(), ctx);
		return 1;
	}

	public static int destroyAll(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		// TODO optimize later
		SMGData.getInstance().emptyTeams();
		PacketDistributor.sendToAllPlayers(new TeamPayloads.SyncTeams(new ArrayList<>()));

		print("Successfully destroyed all the teams!", ctx);
		return 1;
	}

	public static int clean(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		String name = StringArgumentType.getString(ctx, "NAME");
		Team team = new Team(name);
		// TODO optimize later
		List<Team> teams = SMGData.getInstance().getTeams();
		if (!teams.contains(team)) {
			print("The server does not have a team with that name!", ctx);
			return 1;
		}
		// Equals is Overriden to only compare names so it will delete the team with any
		// amount of players even if our object has none
		teams.remove(team);
		teams.add(team);
		PacketDistributor.sendToAllPlayers(new TeamPayloads.SyncTeams(teams));
		print("Successfully cleaned team " + team.getTeamName(), ctx);
		return 1;
	}

	public static int print1(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		List<Team> teams = SMGData.getInstance().getTeams();
		for (Team team : teams) {
			print(team, ctx);
		}

		return 1;
	}

	public static int print2(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		List<Team> teams = SMGData.getInstance().getTeams();
		String name = StringArgumentType.getString(ctx, "NAME");
		Team teamTemplate = new Team(name);
		if (!teams.contains(teamTemplate)) {
			print("This team does not exist", ctx);
			return 1;
		}

		for (int i = 0; i < teams.size(); i++) {
			if (teams.get(i).equals(teamTemplate)) {
				print(teams.get(i), ctx);
			}
		}

		return 1;
	}

	private static void print(String str, CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		ServerPlayer player = ctx.getSource().getPlayerOrException();
		player.sendSystemMessage(Component.literal(str));
	}

	private static void print(Team team, CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		ServerPlayer player = ctx.getSource().getPlayerOrException();
		print(team.getTeamName(), ctx);
		for (String name : team.getPlayerNicknames()) {
			player.sendSystemMessage(Component.literal(name));
		}
	}
	
	
	
	public static int add(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		String name = StringArgumentType.getString(ctx, "NAME");
		List<Team> teams = SMGData.getInstance().getTeams();
		Team team = Team.getTeam(name, teams);
		if (team == null) {
			print("Team with this name does not exist yet!", ctx);
			return 1;
		}
		Collection<ServerPlayer> players = EntityArgument.getPlayers(ctx, "PLAYERS");
		
		for(ServerPlayer player : players) {
			team.addPlayer(player.getName().getString());
		}
		
		//TODO optimize later
		PacketDistributor.sendToAllPlayers(new TeamPayloads.SyncTeams(teams));
		
		print("Successfully added players to the team: " + team.getTeamName(), ctx);
		
		return 1;
	}
	
	public static int remove(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		
		String name = StringArgumentType.getString(ctx, "NAME");
		List<Team> teams = SMGData.getInstance().getTeams();
		Team team = Team.getTeam(name, teams);
		if (team == null) {
			print("Team with this name does not exist yet!", ctx);
			return 1;
		}
		Collection<ServerPlayer> players = EntityArgument.getPlayers(ctx, "PLAYERS");
		
		for(ServerPlayer player : players) {
			team.removePlayer(player.getName().getString());
		}
		
		//TODO optimize later
		PacketDistributor.sendToAllPlayers(new TeamPayloads.SyncTeams(teams));
		
		print("Successfully removed players to the team: " + team.getTeamName(), ctx);
		
		return 1;
	}
	
	

}
