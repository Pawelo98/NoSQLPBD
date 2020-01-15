package app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.ClubDAO;
import app.entity.Building;
import app.entity.Club;

@Service
public class ClubServiceImpl implements ClubService {

	@Override
	@Transactional
	public List<Object[]> getClubsHomeGoals(int theId) {
		return clubDAO.getClubsHomeGoals(theId);
	}
	
	@Override
	@Transactional
	public List<Object[]> getClubsAwayGoals(int theId) {
		return clubDAO.getClubsAwayGoals(theId);
	}
	
	@Override
	@Transactional
	public List<Object[]> getClubsHomeGoalsConceded(int theId) {
		return clubDAO.getClubsHomeGoalsConceded(theId);
	}
	
	@Override
	@Transactional
	public List<Object[]> getClubsAwayGoalsConceded(int theId) {
		return clubDAO.getClubsAwayGoalsConceded(theId);
	}
	
	@Override
	@Transactional
	public List<Long> getClubsHomePoints(int theId) {
		return clubDAO.getClubsHomePoints(theId);
	}
	
	@Override
	@Transactional
	public List<Long> getClubsAwayPoints(int theId) {
		return clubDAO.getClubsAwayPoints(theId);
	}
	
	@Autowired
	private ClubDAO clubDAO;
	
	@Override
	@Transactional
	public List<Club> getClubs() {
		return clubDAO.getClubs();
	}

	@Override
	@Transactional
	public void saveClub(Club club) {
		clubDAO.saveClub(club);
	}

	@Override
	@Transactional
	public List<Integer> getPoints(int theId) {
		return clubDAO.getPoints(theId);
	}

}
