package app.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import app.dao.UserDAO;
import app.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "users";

	@Override
	public List<User> getUsers() {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<User> query = session.createQuery("from User", User.class);
//		//  user select user join fetch user. where c.id = :id
//		List<User> users = query.getResultList();
//		
//		return users;
		return mongoTemplate.findAll(User.class, COLLECTION_NAME);
	}
	
	@Override
	public void addUser(User user) {
		if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);

        }
        mongoTemplate.insert(user, COLLECTION_NAME);
	}

	@Override
	public void saveUser(User user) {

//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(user);
		mongoTemplate.save(user);
	}

	@Override
	public User getUser(String username) {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		User user = session.get(User.class, username);
//		
//		return user;
		return mongoTemplate.findById(username, User.class);
	}

	@Override
	public void deleteUser(String username) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query query = session.createQuery("delete from User where username=:usernameFromView");
//		
//		query.setParameter("usernameFromView", username);
//		
//		query.executeUpdate();
		User user = mongoTemplate.findById(username, User.class);
		mongoTemplate.remove(user, COLLECTION_NAME);
	}

}
