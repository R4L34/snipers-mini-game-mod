package com.r4l.snipers_mini_game_mod.settings;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class Team {
    
    public List<String> players;
    
    public Team() {
    	players = new ArrayList<>();
    }
    
    public List<EntityPlayer> getPlayers(World world) {
        List<EntityPlayer> team = new ArrayList<>();
        if (world == null || players == null) {
            return team;
        }
        for (String p : players) {
            EntityPlayer player = world.getPlayerEntityByName(p);
            if (player != null) {
                team.add(player);
            }
        }
        return team;
    }

    // --- NBT Serialization ---

    public void readFromNBT(NBTTagCompound nbt) {
        players.clear();
        NBTTagList list = nbt.getTagList("Players", Constants.NBT.TAG_STRING);
        for(int i = 0; i < list.tagCount(); i++) {
            players.add(list.getStringTagAt(i));
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        for(String p : players) {
            list.appendTag(new NBTTagString(p));
        }
        nbt.setTag("Players", list);
        return nbt;
    }
}