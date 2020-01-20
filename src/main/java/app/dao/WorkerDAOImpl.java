package app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
		mongoTemplate.save(worker);
	}
	
	@Override
	public Worker getWorker(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Worker worker = session.get(Worker.class, theId);
//		return worker;
		return mongoTemplate.findById(theId, Worker.class);
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
