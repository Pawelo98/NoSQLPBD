package app.service;

import java.util.List;


import app.entity.Match;
import app.entity.Worker;

public interface MatchService {
	
	public List<Match> getMatches();

	public void saveMatch(Match match);

	public Match getMatch(int theId);

	public void deleteMatch(int theId);
	

	public List<Match> getMatches(int theId);
	public List<Match> getFutureMatchesForClub(int theId);
	
	public List<Match> getPastMatchesForClub(int theId);

}
