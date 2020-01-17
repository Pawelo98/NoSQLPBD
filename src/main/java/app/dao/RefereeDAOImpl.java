package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import app.entity.Referee;

@Repository
public class RefereeDAOImpl implements RefereeDAO {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	private static final String COLLECTION_NAME="referees";
	
	@Override
	public List<Referee> getReferees() {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Referee> query = session.createQuery("from Referee", Referee.class);
//		
//		List<Referee> referees = query.getResultList();
		
		return null;
	}

	@Override
	public void saveReferee(Referee referee) {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(referee);
	}

	@Override
	public Referee getReferee(int theId) {
		
//		Session session = sessionFactory.getCurrentSession();
//		Referee referee = session.get(Referee.class, theId);
		
		return null;
	}

	@Override
	public void deleteReferee(int theId) {
		
//		Session session = sessionFactory.getCurrentSession();
//
//		Query theQuery = session.createQuery("delete from Referee where id=:refereeId");
//		theQuery.setParameter("refereeId", theId);
//		
//		theQuery.executeUpdate();
		
	}
	
	@Override
	public Referee findRefereeById(int theId) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(theId, Referee.class);
	}

	@Override
	public List<Referee> findAllReferees() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Referee.class,COLLECTION_NAME );
	}

}
