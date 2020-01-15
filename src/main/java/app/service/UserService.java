package app.service;

import java.util.List;

import app.entity.League;
import app.entity.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public List<League> getLeagues();

	public void saveUser(User user);
	
	public void saveLeague(League league);

	public User getUser(String username);

	public void deleteUser(String username);
}
