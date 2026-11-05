package com.r4l.snipers_mini_game.team;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {

	private final String teamName;
	private final List<String> playerNicknames;

	public Team(String teamName) {
		this.teamName = teamName;
		playerNicknames = new ArrayList<>();
	}

	public Team(String teamName, List<String> teams) {
		this.teamName = teamName;
		playerNicknames = teams;
	}

	public String getTeamName() {
		return teamName;
	}

	public List<String> getPlayerNicknames() {
		return Collections.unmodifiableList(playerNicknames);
	}

	public void addPlayer(String nickname) {
		if (!playerNicknames.contains(nickname)) {
			playerNicknames.add(nickname);
		}
	}

	public void removePlayer(String nickname) {
		playerNicknames.remove(nickname);
	}
	
	public boolean hasPlayer(Player player) {
		return playerNicknames.contains(player.getName().getString());
	}

	public List<ServerPlayer> getPlayerListServer(MinecraftServer server) {
		List<ServerPlayer> result = new ArrayList<>();
		PlayerList playerList = server.getPlayerList();
		for (String nickname : playerNicknames) {
			ServerPlayer player = playerList.getPlayerByName(nickname);
			if (player != null) {
				result.add(player);
			}
		}
		return result;
	}

	@OnlyIn(Dist.CLIENT)
	public List<AbstractClientPlayer> getPlayerListClient() {
		List<AbstractClientPlayer> result = new ArrayList<>();
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) {
			return result;
		}
		for (String nickname : playerNicknames) {
			for (var entity : mc.level.players()) {
				if (entity instanceof AbstractClientPlayer clientPlayer
						&& clientPlayer.getGameProfile().getName().equalsIgnoreCase(nickname)) {
					result.add(clientPlayer);
					break;
				}
			}
		}
		return result;
	}

	public static final StreamCodec<FriendlyByteBuf, Team> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8, Team::getTeamName, ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()),
			Team::getPlayerNicknames, Team::new);

	public static final StreamCodec<FriendlyByteBuf, List<Team>> LIST_STREAM_CODEC = Team.STREAM_CODEC
			.apply(ByteBufCodecs.list());

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Team team) {
			if (teamName.equals(team.getTeamName())) {
				return true;
			}
		}

		return false;
	}
	
	
	public static Team getTeam(String name, List<Team> teams) {
		for(Team team : teams) {
			if(team.getTeamName().equals(name)) {
				return team;
			}
		}
		return null;
	}
	
	public static boolean isOnTheSameTeam(List<Team> teams, Player p1, Player p2) {
		
		for (Team team : teams) {
			
			if(team.hasPlayer(p1) && team.hasPlayer(p2)) {
				return true;
			}
			
		}
		
		return false;
	}

}
