package com.r4l.snipers_mini_game.network.client;

import com.r4l.snipers_mini_game.SMGData;
import com.r4l.snipers_mini_game.network.payloads.TeamPayloads.SyncTeams;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class TeamClientPH {
	
	public static void handleSyncMultiple(final SyncTeams payload, final IPayloadContext context) {
        SMGData data = SMGData.getInstance();
        data.setTeams(payload.teams());
        //context.player().sendSystemMessage(Component.literal("Sync Successfull"));
    }

}
