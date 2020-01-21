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
import app.entity.Club;
import app.entity.Match;
import app.entity.User;

@Repository
public class ClubDAOImpl implements ClubDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "clubs";
		
	@Override
	public List<Object[]> getClubsHomeGoals(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Object[]> query = session.createQuery("select m.host.name, sum(m.home_goals), count(m) " +
//				"from Match m " +
//				"where m.league.league_id=:leagueID and m.game_date <= current_date " + 
//				"group by m.host.club_id", Object[].class);
//		query.setParameter("leagueID", theId);
//		
//		List<Object[]> clubs = query.getResultList();
		List<Object[]> clubs = new ArrayList<Object[]>();
		
		return clubs;
	}
	
	@Override
	public List<Object[]> getClubsHomeGoalsConceded(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Object[]> query = session.createQuery("select m.host.name, sum(m.away_goals) " +
//				"from Match m " +
//				"where m.league.league_id=:leagueID and m.game_date <= current_date " + 
//				"group by m.host.club_id", Object[].class);
//		query.setParameter("leagueID", theId);
//		
//		List<Object[]> clubs = query.getResultList();
		List<Object[]> clubs = new ArrayList<Object[]>();
		return clubs;
	}
	
	@Override
	public List<Object[]> getClubsAwayGoals(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//
//		Query<Object[]> query = session.createQuery("select m.visitor.name, sum(m.away_goals), count(m) " +
//				"from Match m " +
//				"where m.league.league_id=:leagueID and m.game_date <= current_date " + 
//				"group by m.visitor.club_id", Object[].class);
//		query.setParameter("leagueID", theId);
//		
//		List<Object[]> clubs = query.getResultList();
		List<Object[]> clubs = new ArrayList<Object[]>();
		return clubs;
	}
	
	@Override
	public List<Object[]> getClubsAwayGoalsConceded(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//
//		Query<Object[]> query = session.createQuery("select m.visitor.name, sum(m.home_goals) " +
//				"from Match m " +
//				"where m.league.league_id=:leagueID and m.game_date <= current_date " + 
//				"group by m.visitor.club_id", Object[].class);
//		query.setParameter("leagueID", theId);
//		
//		List<Object[]> clubs = query.getResultList();
		List<Object[]> clubs = new ArrayList<Object[]>();
		return clubs;
	}
	
	@Override
	public List<Long> getClubsHomePoints(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//
//		Query<Long> query = session.createQuery("select sum( case when m.home_goals > m.away_goals then 3 "
//				+ "when m.home_goals = m.away_goals then 1 else 0 end) " +
//				"from Match m " +
//				"where m.league.league_id=:leagueID and m.game_date <= current_date " + 
//				"group by m.host.club_id", Long.class);
//		query.setParameter("leagueID", theId);
//		
//		List<Long> clubs = query.getResultList();
		List<Long> clubs = new ArrayList<Long>();
		return clubs;
	}
	
	@Override
	public List<Long> getClubsAwayPoints(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//
//		Query<Long> query = session.createQuery("select sum( case when m.home_goals > m.away_goals then 0 "
//				+ "when m.home_goals = m.away_goals then 1 else 3 end) " +
//				"from Match m " +
//				"where m.league.league_id=:leagueID and m.game_date <= current_date " + 
//				"group by m.host.club_id", Long.class);
//		query.setParameter("leagueID", theId);
//		
//		List<Long> clubs = query.getResultList();
		List<Long> clubs = new ArrayList<Long>();
		return clubs;
	}
	
	@Override
	public List<Club> getClubs() {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Club> query = session.createQuery("from Club", Club.class);
//		
//		List<Club> clubs = query.getResultList();
//		
//		return clubs;
		return mongoTemplate.findAll(Club.class, COLLECTION_NAME);
	}
	
	@Override
	public void addClub(Club club) {
		if (!mongoTemplate.collectionExists(Club.class)) {
            mongoTemplate.createCollection(Club.class);

        }
        mongoTemplate.insert(club, COLLECTION_NAME);
	}

	@Override
	public void saveClub(Club club) {

//		Session session = sessionFactory.getCurrentSession();
//		
//		session.save(club);
		mongoTemplate.save(club);
	}

	@Override
	public List<Integer> getPoints(int theId) {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Integer> query = session.createQuery("select count(hostMatches) c from Club c join fetch c.hostMatches l where l.league.league_id=:leagueID", Integer.class);
//		query.setParameter("leagueID", theId);
		
		//List<Club> clubs = query.getResultList();
		
		return null;
	}

}
