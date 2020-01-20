package app.dao;

import java.util.List;

import app.entity.Match;

import app.entity.User;


public interface MatchDAO {

	public List<Match> getPastMatches(int id);
	
	public List<Match> getFutureMatches(int id);

	void saveMatch(Match match);
	
	public Match getMatch(int theId);

	public void deleteMatch(int theId);

	List<Match> getMatches();

	
	public List<Match> getMatchesForDate();
	
	public List<Match> getMatches(int theId);


	List<Match> getPastMatchesForClub(int theId);

	List<Match> getFutureMatchesForClub(int theId);

	void addMatch(Match match);
}
