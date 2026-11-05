package com.r4l.snipers_mini_game;

import java.util.ArrayList;
import java.util.List;

import com.r4l.snipers_mini_game.team.Team;

public class SMGData {
	
	private static SMGData instance;
	
	private List<Team> teams;
	
	private SMGData() {
		setTeams(new ArrayList<>());
	}
	
	public static SMGData getInstance() {
		if (instance == null) {
			instance = new SMGData();
		}
		return instance;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public void emptyTeams() {
		this.teams = new ArrayList<>();
	}
	
	
	

}
