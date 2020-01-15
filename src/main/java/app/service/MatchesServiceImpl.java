package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.MatchDAO;
import app.entity.Match;
import app.entity.User;

@Service
public class MatchesServiceImpl implements MatchesService {

	@Autowired
	private MatchDAO matchDAO;
	
	@Override
	@Transactional
	public List<Match> getPastMatches(int id) {
		return matchDAO.getPastMatches(id);
	}
	
	@Override
	@Transactional
	public List<Match> getFutureMatches(int id) {
		return matchDAO.getFutureMatches(id);
	}
	
	@Override
	@Transactional
	public List<Match> getMatches() {
		return matchDAO.getMatches();
	}
	
	@Override
	@Transactional
	public List<Match> getMatchesForDate() {
		return matchDAO.getMatchesForDate();
	}


}
