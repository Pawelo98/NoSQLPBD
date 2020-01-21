package app.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

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
		if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);

        }
        mongoTemplate.save(user, COLLECTION_NAME);
	}

	@Override
	public User getUser(String username) {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		User user = session.get(User.class, username);
//		
//		return user;
		//BasicQuery query = (BasicQuery) new BasicQuery("{'username': 'admin' }").limit(1);
		
		Query query = new Query(Criteria.where("username").is(username));
		
		User user = mongoTemplate.findOne(query, User.class, "users");
		
		return user;
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
		User user = this.getUser(username);
		mongoTemplate.remove(user, COLLECTION_NAME);
	}

}
