package app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.entity.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveAuthority(Authority auth) {
		Session session = sessionFactory.getCurrentSession();
		session.save(auth);
	}

}
