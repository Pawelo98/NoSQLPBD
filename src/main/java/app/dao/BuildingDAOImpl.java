package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import app.entity.Building;
import app.entity.Referee;
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
		return mongoTemplate.find(quer, Building.class, COLLECTION_NAME);
		
		
	}
	
	@Override
	public void addBuilding(Building building) {
		if (!mongoTemplate.collectionExists(Building.class)) {
            mongoTemplate.createCollection(Building.class);

        }
        mongoTemplate.save(building, COLLECTION_NAME);
	}

	@Override
	public void saveBuilding(Building building) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(building);
		
//		if (!mongoTemplate.collectionExists(Building.class)) {
//            mongoTemplate.createCollection(Building.class);
//
//        }
//		mongoTemplate.save(building, COLLECTION_NAME);
		
		Query query = new Query(Criteria.where("building_id").is(building.getBuilding_id()));
		Update update = Update.update("address", building.getAddress());
        Update update2 = Update.update("name", building.getName());
        Update update3 = Update.update("surface", building.getSurface());
        Update update4 = Update.update("type", building.getType());
        mongoTemplate.updateFirst(query, update, Building.class);
        mongoTemplate.updateFirst(query, update2, Building.class);
        mongoTemplate.updateFirst(query, update3, Building.class);
        mongoTemplate.updateFirst(query, update4, Building.class);
	}

	@Override
	public Building getBuilding(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Building theBuilding = session.get(Building.class, theId);
//		return theBuilding;
		Query query = new Query(Criteria.where("building_id").is(theId));
		return mongoTemplate.findOne(query, Building.class, COLLECTION_NAME);
		
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
