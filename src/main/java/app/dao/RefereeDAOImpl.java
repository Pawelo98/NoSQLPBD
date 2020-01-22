package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import app.entity.Referee;
import app.entity.User;

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
		
		return mongoTemplate.findAll(Referee.class, COLLECTION_NAME);
	}
	

	@Override
	public void saveReferee(Referee referee) {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(referee);
		if (!mongoTemplate.collectionExists(Referee.class)) {
            mongoTemplate.createCollection(Referee.class);

        }       
			mongoTemplate.save(referee, COLLECTION_NAME);
	
	
	}
	
	@Override
	public void updateReferee(Referee referee) {
		
		Query query = new Query(Criteria.where("referee_id").is(referee.getReferee_id()));
		Update update = Update.update("surname", referee.getSurname());
        Update update2 = Update.update("name", referee.getName());
        Update update3 = Update.update("nationality", referee.getNationality());
        mongoTemplate.updateFirst(query, update, Referee.class);
        mongoTemplate.updateFirst(query, update2, Referee.class);
        mongoTemplate.updateFirst(query, update3, Referee.class);
	}
	

	@Override
	public Referee getReferee(int theId) {
		
//		Session session = sessionFactory.getCurrentSession();
//		Referee referee = session.get(Referee.class, theId);
		Query query = new Query(Criteria.where("referee_id").is(theId));
		return mongoTemplate.findOne(query, Referee.class, COLLECTION_NAME);
//		mongoTemplate.findOne(query(where("Email").is(Email)), User.class,COLLECTION_NAME);
	}

	@Override
	public void deleteReferee(int theId) {
		
//		Session session = sessionFactory.getCurrentSession();
//
//		Query theQuery = session.createQuery("delete from Referee where id=:refereeId");
//		theQuery.setParameter("refereeId", theId);
//		
//		theQuery.executeUpdate();
		Referee referee = mongoTemplate.findById(theId, Referee.class);
		mongoTemplate.remove(referee, COLLECTION_NAME);
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
