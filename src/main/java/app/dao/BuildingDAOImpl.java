package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.entity.Building;

@Repository
public class BuildingDAOImpl implements BuildingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Building> getBuildings(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
//		Query<Building> query = session.createQuery("from Building as b left join b.Club as c where", Building.class);
		
//		Query<Building> query = session.createQuery("select b from Building b where b.club.club_id=2", Building.class);
		
		Query<Building> query = session.createQuery("from Building where club.club_id=:clubID", Building.class);
		query.setParameter("clubID", theId);
//		query.executeUpdate();
//		Query<Building> query = session.createQuery("from Building", Building.class);
//		Query<Building> query = session.createQuery("from Building where building_id = 1", Building.class);
//		query.setFirstResult(0);
//		query.setMaxResults(31);
		
		List<Building> clubs = query.getResultList();
		
		return clubs;
	}

	@Override
	public void saveBuilding(Building building) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(building);
	}

	@Override
	public Building getBuilding(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Building theBuilding = session.get(Building.class, theId);
		return theBuilding;
	}

	@Override
	public void deleteBuilding(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Building where building_id=:buildingId");
		query.setParameter("buildingId", theId);
		query.executeUpdate();
		
	}

}
