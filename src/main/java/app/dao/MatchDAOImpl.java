package app.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import app.entity.Club;
import app.entity.Invite;
import app.entity.Match;

import app.entity.User;


@Repository
public class MatchDAOImpl implements MatchDAO {

//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "matches";
	
	@Override
	public List<Match> getPastMatches(int id) {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Match> query = session.createQuery("select matchtemp from Match matchtemp "
//				+ "where (matchtemp.host.club_id = :id or matchtemp.visitor.club_id = :id) "
//				+ "and matchtemp.game_date < current_date "
//				+ "order by matchtemp.game_date desc"
//				, Match.class);
//		
//		query.setParameter("id", id);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(5);
//		
//		List<Match> matches = query.list();
		Criteria criteria = new Criteria();
		
		Query query = new Query(criteria.orOperator(Criteria.where("host").is(id), Criteria.where("visitor").is(id)).and("game_date").lt("2020-01-23"));
		//Query query = new Query(Criteria.where("host").is(id)).limit(5);
		query.with(new Sort(Sort.Direction.DESC, "game_date"));
		
		query.limit(5);
		
		List<Match> matches = mongoTemplate.find(query, Match.class, "matches");
		
		return matches;
	}
	
	@Override
	public List<Match> getFutureMatches(int id) {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Match> query = session.createQuery("select matchtemp from Match matchtemp "
//				+ "where (matchtemp.host.club_id = :id or matchtemp.visitor.club_id = :id) "
//				+ "and matchtemp.game_date > current_date "
//				+ "order by matchtemp.game_date"
//				, Match.class);
//		
//		query.setParameter("id", id);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(5);
//		
//		List<Match> matches = query.list();
		Date now = new Date();
		Criteria criteria = new Criteria();
		
		Query query = new Query(criteria.orOperator(Criteria.where("host").is(id), Criteria.where("visitor").is(id)).and("game_date").gt("2020-01-23")).limit(5);
		
		query.with(new Sort(Sort.Direction.ASC, "game_date"));
		
		List<Match> matches = mongoTemplate.find(query, Match.class, "matches");
		
		return matches;
	}
	
	@Override
	public List<Match> getPastMatchesForClub(int theId) {
//		Session session = sessionFactory.getCurrentSession();
////		Query<Match> query = session.createQuery("select matchtemp from Match matchtemp "
////				+ "where (matchtemp.host.club_id = 1 or matchtemp.visitor.club_id = 1) "
////				+ "and matchtemp.game_date < current_date "
////				+ "order by matchtemp.game_date desc"
////				, Match.class);
//		Query<Match> query = session.createQuery("from Match where (host.club_id=:clubID or visitor.club_id=:clubID)"
//												+ " and game_date < current_date"
//												+ " order by game_date desc", Match.class);
//		query.setParameter("clubID", theId);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(10);
//		
//		List<Match> matches = query.list();
		Criteria criteria = new Criteria();
		
		Query query = new Query(criteria.orOperator(Criteria.where("host").is(theId), Criteria.where("visitor").is(theId)).and("game_date").lt("2020-01-23"));
		//Query query = new Query(Criteria.where("host").is(id)).limit(5);
		query.with(new Sort(Sort.Direction.DESC, "game_date"));
		
		query.limit(5);
		
		List<Match> matches = mongoTemplate.find(query, Match.class, "matches");
		
		return matches;
	}
	
	@Override
	public List<Match> getFutureMatchesForClub(int theId) {
//		Session session = sessionFactory.getCurrentSession();
////		Query<Match> query = session.createQuery("select matchtemp from Match matchtemp "
////				+ "where (matchtemp.host.club_id = 1 or matchtemp.visitor.club_id = 1) "
////				+ "and matchtemp.game_date > current_date "
////				+ "order by matchtemp.game_date"
////				, Match.class);
//		
//		Query<Match> query = session.createQuery("from Match where (host.club_id=:clubID or visitor.club_id=:clubID)"
//				+ " and game_date > current_date"
//				+ " order by game_date", Match.class);
//		query.setParameter("clubID", theId);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(10);
//		
//		List<Match> matches = query.list();
		Date now = new Date();
		Criteria criteria = new Criteria();
		
		Query query = new Query(criteria.orOperator(Criteria.where("host").is(theId), Criteria.where("visitor").is(theId)).and("game_date").gt("2020-01-23")).limit(5);
		
		query.with(new Sort(Sort.Direction.ASC, "game_date"));
		
		List<Match> matches = mongoTemplate.find(query, Match.class, "matches");
		
		return matches;
	}

	@Override
	public List<Match> getMatches() {
		
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Match> query = session.createQuery("from Match where host=11 or visitor=11", Match.class);
//		//Query<Match> query = session.createQuery("from Match", Match.class);
//		
//		List<Match> match = query.getResultList();
//		
//		return match;
		return mongoTemplate.findAll(Match.class, COLLECTION_NAME);
	}
	
	@Override
	public List<Match> getMatchesForDate() {
		
		//2019-11-29
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
//		try {
//			date = format.parse("2019-11-29");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Match> query = session.createQuery("select matchtemp from Match matchtemp "
//				+ "where (matchtemp.game_date = :data)", Match.class).setParameter("data", date);
//		//Query<Match> query = session.createQuery("from Match", Match.class);
//		
//		List<Match> matches = query.getResultList();
		List<Match> matches = new ArrayList<Match>();
		Match match = new Match();
		matches.add(match);
		return matches;
	}
	
	@Override
	public List<Match> getMatches(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		
////		Query<Building> query = session.createQuery("from Building as b left join b.Club as c where", Building.class);
//		
////		Query<Building> query = session.createQuery("select b from Building b where b.club.club_id=2", Building.class);
//		
////		Query<Match> query = session.createQuery("from Match where league.league_id=:leagueID", Match.class);
////		query.setParameter("leagueID", theId);   //dobra
//
//		Query<Match> query = session.createQuery("select distinct c from Club c left join fetch c.matches where league.league_id=:leagueID", Match.class);
//		query.setParameter("leagueID", theId);
//
////		query.executeUpdate();
////		Query<Building> query = session.createQuery("from Building", Building.class);
////		Query<Building> query = session.createQuery("from Building where building_id = 1", Building.class);
////		query.setFirstResult(0);
////		query.setMaxResults(31);
//		
//		List<Match> matches = query.getResultList();
		List<Match> matches = new ArrayList<Match>();
		Match match = new Match();
		matches.add(match);
		return matches;
	}
	
	@Override
	public void addMatch(Match match) {
		if (!mongoTemplate.collectionExists(Match.class)) {
            mongoTemplate.createCollection(Match.class);

        }
        mongoTemplate.insert(match, COLLECTION_NAME);
	}

	@Override
	public void saveMatch(Match match) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(match);
		mongoTemplate.save(match);
	}

	@Override
	public Match getMatch(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Match match = session.get(Match.class, theId);
//		return match;
		return mongoTemplate.findById(theId, Match.class);
	}

	@Override
	public void deleteMatch(int theId) {
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("delete from Match where match_id=:matchId");
//		query.setParameter("matchId", theId);
//		query.executeUpdate();
		Match match = mongoTemplate.findById(theId, Match.class);
		mongoTemplate.remove(match, COLLECTION_NAME);
	}

}
