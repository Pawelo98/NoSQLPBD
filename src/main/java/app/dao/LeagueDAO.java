package app.dao;

import java.util.List;

import app.entity.League;

public interface LeagueDAO {

	public List<League> getLeagues();

	public void saveLeague(League league);

	public List<League> findAllLeagues();
}
