package com.r4l.snipers_mini_game.network.payloads;

import java.util.List;

import com.r4l.snipers_mini_game.Reference;
import com.r4l.snipers_mini_game.team.Team;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class TeamPayloads {
	
	public record SyncTeams(List<Team> teams) implements CustomPacketPayload {
        public static final Type<SyncTeams> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "sync_teams"));
        public static final StreamCodec<FriendlyByteBuf, SyncTeams> CODEC = StreamCodec.composite(Team.LIST_STREAM_CODEC, SyncTeams::teams, SyncTeams::new);
        @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
    }
	
	public record SyncTeam(Team team) implements CustomPacketPayload {
        public static final Type<SyncTeam> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MODID, "sync_team"));
        public static final StreamCodec<FriendlyByteBuf, SyncTeam> CODEC = StreamCodec.composite(Team.STREAM_CODEC, SyncTeam::team, SyncTeam::new);
        @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
    }
}
