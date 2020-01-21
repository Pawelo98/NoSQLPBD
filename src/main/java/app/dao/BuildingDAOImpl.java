package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import app.entity.Building;
import app.entity.User;

@Repository
public class BuildingDAOImpl implements BuildingDAO {
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "buildings";

	@Override
	public List<Building> getBuildings(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		
////		Query<Building> query = session.createQuery("from Building as b left join b.Club as c where", Building.class);
//		
////		Query<Building> query = session.createQuery("select b from Building b where b.club.club_id=2", Building.class);
//		
//		Query<Building> query = session.createQuery("from Building where club.club_id=:clubID", Building.class);
//		query.setParameter("clubID", theId);
////		query.executeUpdate();
////		Query<Building> query = session.createQuery("from Building", Building.class);
////		Query<Building> query = session.createQuery("from Building where building_id = 1", Building.class);
////		query.setFirstResult(0);
////		query.setMaxResults(31);
//		
//		List<Building> clubs = query.getResultList();
//		
//		return clubs;
		
//		Query quer = new Query(Criteria.where("club_id").is(1));
		Query quer = new Query(Criteria.where("club").is(theId));
		return mongoTemplate.find(quer, Building.class,"buildings");
	}
	
	@Override
	public void addBuilding(Building building) {
		if (!mongoTemplate.collectionExists(Building.class)) {
            mongoTemplate.createCollection(Building.class);

        }
        mongoTemplate.insert(building, COLLECTION_NAME);
	}

	@Override
	public void saveBuilding(Building building) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(building);
		
		if (!mongoTemplate.collectionExists(Building.class)) {
            mongoTemplate.createCollection(Building.class);

        }
		mongoTemplate.save(building, COLLECTION_NAME);
	}

	@Override
	public Building getBuilding(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Building theBuilding = session.get(Building.class, theId);
//		return theBuilding;
		return mongoTemplate.findById(theId, Building.class);
	}

	@Override
	public void deleteBuilding(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("delete from Building where building_id=:buildingId");
//		query.setParameter("buildingId", theId);
//		query.executeUpdate();
		
		Building building = mongoTemplate.findById(theId, Building.class);
		mongoTemplate.remove(building, COLLECTION_NAME);
	}

}
