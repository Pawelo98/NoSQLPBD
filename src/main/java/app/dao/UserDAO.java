package app.dao;

import java.util.List;

import app.entity.User;

public interface UserDAO {
	
	public List<User> getUsers();

	public void saveUser(User user);

	public User getUser(String username);

	public void deleteUser(String username);
	
}
