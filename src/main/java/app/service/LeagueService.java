package app.service;

import java.util.List;

import app.entity.League;

public interface LeagueService {
	public List<League> getLeagues();

	public List<League> getLeaguesMongo();
}
