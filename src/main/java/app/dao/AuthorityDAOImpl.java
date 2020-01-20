package app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import app.entity.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "authorities";

	@Override
	public void saveAuthority(Authority auth) {
//		Session session = sessionFactory.getCurrentSession();
//		session.save(auth);
		mongoTemplate.save(auth);
	}

}
