package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.MatchDAO;
import app.entity.Club;
import app.entity.Match;

@Service
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private MatchDAO matchDAO;
	
	@Override
	@Transactional
	public List<Match> getMatches(int theId) {
		return matchDAO.getMatches(theId);
	}
	
	
	@Override
	@Transactional
	public List<Match> getMatches() {
		return matchDAO.getMatches();
	}

	@Override
	@Transactional
	public void saveMatch(Match match) {
		matchDAO.saveMatch(match);
	}
	
	@Override
	@Transactional
	public List<Match> getPastMatchesForClub(int theId) {
		return matchDAO.getPastMatchesForClub(theId);
	}
	
	@Override
	@Transactional
	public List<Match> getFutureMatchesForClub(int theId) {
		return matchDAO.getFutureMatchesForClub(theId);
	}

	@Override
	@Transactional
	public Match getMatch(int theId) {
		return matchDAO.getMatch(theId);
	}

	@Override
	@Transactional
	public void deleteMatch(int theId) {
		matchDAO.deleteMatch(theId);
		
	}
}
