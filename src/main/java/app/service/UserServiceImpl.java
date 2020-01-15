package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.LeagueDAO;
import app.dao.UserDAO;
import app.entity.League;
import app.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LeagueDAO leagueDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
	@Override
	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);
	}
	
	@Override
	@Transactional
	public List<League> getLeagues() {
		return leagueDAO.getLeagues();
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public void saveLeague(League league) {
		leagueDAO.saveLeague(league);
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
	}

}
