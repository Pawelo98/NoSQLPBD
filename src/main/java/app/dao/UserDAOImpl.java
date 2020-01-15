package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.UserDAO;
import app.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("from User", User.class);
		//  user select user join fetch user. where c.id = :id
		List<User> users = query.getResultList();
		
		return users;
	}

	@Override
	public void saveUser(User user) {

		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(user);
	}

	@Override
	public User getUser(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, username);
		
		return user;
	}

	@Override
	public void deleteUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from User where username=:usernameFromView");
		
		query.setParameter("usernameFromView", username);
		
		query.executeUpdate();
	}

}
