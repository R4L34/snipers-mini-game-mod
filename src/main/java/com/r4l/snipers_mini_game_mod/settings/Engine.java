package com.r4l.snipers_mini_game_mod.settings;

import java.util.ArrayList;
import java.util.List;

import com.r4l.snipers_mini_game_mod.Reference;
import com.r4l.snipers_mini_game_mod.network.NetworkHandler;
import com.r4l.snipers_mini_game_mod.network.message.SyncEnginePacket;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

public class Engine extends WorldSavedData {
	
	public static String DATA_NAME = Reference.MOD_ID + "_Settings";
	
	// Variables initialized to prevent NullPointerExceptions
	private boolean isLeftHanded;
	private List<Team> teams;
	private Team spectators;
	private boolean divideVoice;

	public Engine() {
		super(DATA_NAME);
		isLeftHanded = false;
		teams = new ArrayList<>();
		spectators = new Team();
		divideVoice = false;
	}
	
	public Engine(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.isLeftHanded = nbt.getBoolean("IsLeftHanded");
		this.divideVoice = nbt.getBoolean("DivideVoice");
		
		// Read Teams
		this.teams.clear();
		NBTTagList teamsList = nbt.getTagList("Teams", Constants.NBT.TAG_COMPOUND);
		for(int i = 0; i < teamsList.tagCount(); i++) {
			Team t = new Team();
			t.readFromNBT(teamsList.getCompoundTagAt(i));
			this.teams.add(t);
		}
		
		// Read Spectators
		if(nbt.hasKey("Spectators", Constants.NBT.TAG_COMPOUND)) {
			this.spectators.readFromNBT(nbt.getCompoundTag("Spectators"));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("IsLeftHanded", this.isLeftHanded);
		compound.setBoolean("DivideVoice", this.divideVoice);
		
		// Write Teams
		NBTTagList teamsList = new NBTTagList();
		for(Team t : this.teams) {
			teamsList.appendTag(t.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag("Teams", teamsList);
		
		// Write Spectators
		compound.setTag("Spectators", this.spectators.writeToNBT(new NBTTagCompound()));
		
		return compound;
	}
	
	public static Engine getInstance(World world) {
		MapStorage storage = world.getMapStorage();
		if(storage != null) {
			Engine instance = (Engine) storage.getOrLoadData(Engine.class, DATA_NAME);
			if (instance == null) {
				instance = new Engine();
				storage.setData(DATA_NAME, instance);
			}
			return instance;
		}
		return new Engine();
	}

    // --- GETTERS AND SETTERS ---
	
	public boolean isDivideVoice() {
		return divideVoice;
	}

	public void setDivideVoice(boolean divideVoice) {
		this.divideVoice = divideVoice;
		this.syncAndSave();
	}

	public boolean isLeftHanded() {
		return isLeftHanded;
	}

	public void setLeftHanded(boolean leftHanded) {
		this.isLeftHanded = leftHanded;
		this.syncAndSave();
	}

	public List<Team> getTeams() {
		return teams;
	}

    // If manipulating teams directly (e.g. adding a player to a specific team list),
    // you must manually call syncAndSave() afterward since it bypasses setters!
	public void setTeams(List<Team> teams) {
		this.teams = teams;
		this.syncAndSave();
	}

	public Team getSpectators() {
		return spectators;
	}

	public void setSpectators(Team spectators) {
		this.spectators = spectators;
		this.syncAndSave();
	}

    // --- SYNC LOGIC ---

	/**
	 * Marks data to be saved to disk and fires a network packet to sync it to clients.
	 */
	public void syncAndSave() {
		this.markDirty();
		
		// Generate the NBT data of current state
		NBTTagCompound currentData = new NBTTagCompound();
		this.writeToNBT(currentData);
		
		// Send to all clients via Forge SimpleImpl Network Wrapper
        // You will need to setup this PacketHandler if you haven't already.
		NetworkHandler.channel.sendToAll(new SyncEnginePacket(currentData));
	}
    
    /**
     * Call this ONLY from the Client-Side packet handler to update the client's Engine instance.
     */
    public void updateFromPacket(NBTTagCompound nbt) {
        this.readFromNBT(nbt);
    }
}