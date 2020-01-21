package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import app.entity.League;
import app.entity.Referee;
import app.entity.User;

@Repository
public class LeagueDAOImpl implements LeagueDAO {

//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "leagues";
	
	@Override
	public List<League> getLeagues() {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<League> query = session.createQuery("from League", League.class);
//		
//		List<League> leagues = query.getResultList();
//		
//		return leagues;
		return mongoTemplate.findAll(League.class, COLLECTION_NAME);
	}

	@Override
	public void saveLeague(League league) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.save(league);
		mongoTemplate.save(league);
	}
	
	
	@Override
	public List<League> findAllLeagues() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(League.class,COLLECTION_NAME );
	}

}
