package app.service;

import java.util.List;

import app.entity.Match;
import app.entity.User;

public interface MatchesService {

//	public List<Match> getPastMatches();
//	public List<Match> getFutureMatches();
	public List<Match> getMatches();
	public List<Match> getMatchesForDate();

	public List<Match> getPastMatches(int id);
	public List<Match> getFutureMatches(int id);

}
