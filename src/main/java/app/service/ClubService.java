package app.service;


import java.util.List;

import app.entity.Club;

public interface ClubService {

	public List<Club> getClubs();

	public void saveClub(Club club);

	public List<Object[]> getClubsHomeGoals(int theId);
	public List<Object[]> getClubsAwayGoals(int theId);
	
	public List<Object[]> getClubsHomeGoalsConceded(int theId);
	public List<Object[]> getClubsAwayGoalsConceded(int theId);
	
	public List<Long> getClubsHomePoints(int theId);
	public List<Long> getClubsAwayPoints(int theId);

	public List<Integer> getPoints(int theId);
}
