package app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import app.entity.Building;
import app.entity.User;
import app.entity.Worker;

@Repository
public class WorkerDAOImpl implements WorkerDAO {
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "workers";

	@Override
	public List<Worker> getWorkers(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Worker> query = session.createQuery("from Worker where club.club_id=:clubID", Worker.class);
//		//Query<Worker> query = session.createQuery("select   from Worker w where club.club_id=:clubID", Worker.class);
//		query.setParameter("clubID", theId);
//		
//		List<Worker> workers = query.getResultList();
		List<Worker> workers = new ArrayList<Worker>();
		return workers;
	}
	
	@Override
	public void addWorker(Worker worker) {
		if (!mongoTemplate.collectionExists(Worker.class)) {
            mongoTemplate.createCollection(Worker.class);

        }
        mongoTemplate.insert(worker, COLLECTION_NAME);
	}

	@Override
	public void saveWorker(Worker worker) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(worker);
		//mongoTemplate.save(worker, COLLECTION_NAME);
		
		Query query = new Query(Criteria.where("worker_id").is(worker.getWorker_id()));
		Update update = Update.update("name", worker.getName());
		Update update2 = Update.update("surname", worker.getSurname());
		Update update3 = Update.update("earnings", worker.getEarnings());
		Update update4 = Update.update("department", worker.getDepartment());
		Update update5 = Update.update("shirtNumber", worker.getShirtNumber());
		Update update6 = Update.update("height", worker.getHeight());
		Update update7 = Update.update("weight", worker.getWeight());
		
		Update update8 = Update.update("department", worker.getDepartment());
        mongoTemplate.updateFirst(query, update, Worker.class);
        mongoTemplate.updateFirst(query, update2, Worker.class);
        mongoTemplate.updateFirst(query, update3, Worker.class);
        mongoTemplate.updateFirst(query, update4, Worker.class);
        mongoTemplate.updateFirst(query, update5, Worker.class);
        mongoTemplate.updateFirst(query, update6, Worker.class);
        mongoTemplate.updateFirst(query, update7, Worker.class);
        mongoTemplate.updateFirst(query, update8, Worker.class);
        
 
	}
	
	@Override
	public Worker getWorker(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Worker worker = session.get(Worker.class, theId);
//		return worker;
		//return mongoTemplate.findById(theId, Worker.class);
		Query query = new Query(Criteria.where("worker_id").is(theId));
		return mongoTemplate.findOne(query, Worker.class, COLLECTION_NAME);
	}
	
	@Override
	public void deleteWorker(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("delete from Worker where worker_id=:workerId");
//		query.setParameter("workerId", theId);
//		query.executeUpdate();
		Worker worker = mongoTemplate.findById(theId, Worker.class);
		mongoTemplate.remove(worker, COLLECTION_NAME);
	}

	@Override
	public String getSumOfSalary(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Double> query = session.createQuery("select sum(w.earnings) from Worker w where club.club_id=:clubID");
//		//Query<Double> query = session.createQuery("select w.earnings from Worker w where club.club_id=1 and w.worker_id=2");
//		query.setParameter("clubID", theId);
//		
//		String result = String.valueOf(query.getSingleResult());
//		
//		return result;
		return "";
	}

}
