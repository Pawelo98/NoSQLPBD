package app.dao;

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

import app.entity.Invite;
import app.entity.Match;
import app.entity.Meeting;
import app.entity.User;

@Repository
public class MeetingDAOImpl implements MeetingDAO {
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	private UserDAO userDAO;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "meetings";

	@Override
	public List<Meeting> getMeetingsForUser(String username) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Meeting> query = session.createQuery("select meetingtemp from Meeting meetingtemp "
//				+ "where meetingtemp.initiator.username = :username "
//				+ "order by meetingtemp.meeting_date"
//				, Meeting.class);
//		
//		query.setParameter("username", username);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(15);
//		
//		List<Meeting> meetings = query.list();
		Query query = new Query(Criteria.where("initiator").is(username));
		
		query.with(new Sort(Sort.Direction.ASC, "meeting_date"));
		
		query.limit(10);
		
		List<Meeting> meetings = mongoTemplate.find(query, Meeting.class, "meetings");
		
		//List<Meeting> meetings = new ArrayList<Meeting>();
		return meetings;
	}
	
	@Override
	public void addMeeting(Meeting meeting) {
		if (!mongoTemplate.collectionExists(Meeting.class)) {
            mongoTemplate.createCollection(Meeting.class);

        }
        mongoTemplate.insert(meeting, COLLECTION_NAME);
	}

	@Override
	public void saveMeeting(Meeting meeting) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		session.saveOrUpdate(meeting);
		mongoTemplate.save(meeting);
	}

	@Override
	public List<Meeting> getPastMeetingsForUser(String username) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Meeting> query = session.createQuery("select meetingtemp from Meeting meetingtemp "
//				+ "where meetingtemp.initiator.username = :username "
//				+ "and meetingtemp.meeting_date < current_date "
//				+ "order by meetingtemp.meeting_date desc"
//				, Meeting.class);
//		
//		query.setParameter("username", username);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(5);
//		
//		List<Meeting> meetings = query.list();
		Date now = new Date();
		Query query = new Query(Criteria.where("initiator").is(username).and("meeting_date").lt(now));
		
		query.with(new Sort(Sort.Direction.DESC, "meeting_date"));
		
		query.limit(5);
		
		List<Meeting> meetings = mongoTemplate.find(query, Meeting.class, "meetings");
		
		//List<Meeting> meetings = new ArrayList<Meeting>();
		return meetings;
	}

	@Override
	public List<Meeting> getFutureMeetingsForUser(String username) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Query<Meeting> query = session.createQuery("select meetingtemp from Meeting meetingtemp "
//				+ "where meetingtemp.initiator.username = :username "
//				+ "and meetingtemp.meeting_date > current_date "
//				+ "order by meetingtemp.meeting_date"
//				, Meeting.class);
//		
//		query.setParameter("username", username);
//		
//		query.setFirstResult(0);
//		query.setMaxResults(5);
//		
//		List<Meeting> meetings = query.list();
		Date now = new Date();
		Query query = new Query(Criteria.where("initiator").is(username).and("meeting_date").gt(now)).limit(5);
		
		query.with(new Sort(Sort.Direction.ASC, "meeting_date"));
		
		List<Meeting> meetings = mongoTemplate.find(query, Meeting.class, "meetings");
		
		return meetings;
	}
	
	@Override
	public List<Meeting> getPastInvitesForUser(User user) {
		
		List<Invite>invites = user.getInvites();
		List<Integer> inviteIds = new ArrayList<>();
		if(invites!=null) {
			for(Invite invite : invites) {
				inviteIds.add(invite.getMeeting());
			}
		
		Date now = new Date();
		Query query = new Query(Criteria.where("meeting_id").in(inviteIds).and("meeting_date").lt(now));
		
		query.with(new Sort(Sort.Direction.DESC, "meeting_date"));
		
		query.limit(5);
		
		List<Meeting> meetings = mongoTemplate.find(query, Meeting.class, "meetings");
		
		return meetings;
		
		} else return null;
	}
	
	@Override
	public List<Meeting> getFutureInvitesForUser(User user) {
		
		List<Invite>invites = user.getInvites();
		List<Integer> inviteIds = new ArrayList<>();
		if(invites!=null) {
			for(Invite invite : invites) {
				inviteIds.add(invite.getMeeting());
			}
			
			Date now = new Date();
			Query query = new Query(Criteria.where("meeting_id").in(inviteIds).and("meeting_date").gt(now));
			
			query.with(new Sort(Sort.Direction.ASC, "meeting_date"));
			
			query.limit(5);
			
			List<Meeting> meetings = mongoTemplate.find(query, Meeting.class, "meetings");
			
			return meetings;
		} else return null;
	}

	@Override
	public Meeting getMeeting(int id) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		Meeting meeting = session.get(Meeting.class, id);
//		
//		return meeting;
		Query query = new Query(Criteria.where("meeting_id").is(id));
		
		Meeting meeting = mongoTemplate.findOne(query, Meeting.class, "meetings");
		
		return meeting;
		
		//return mongoTemplate.findById(id, Meeting.class);
	}

	@Override
	public List<Meeting> getInvitesForUser(User user) {
		
		List<Invite>invites = user.getInvites();
		List<Integer> inviteIds = new ArrayList<>();
		if(invites!=null) {
			for(Invite invite : invites) {
				inviteIds.add(invite.getMeeting());
			}
			
			Query query = new Query(Criteria.where("meeting_id").in(inviteIds));
			
			query.with(new Sort(Sort.Direction.ASC, "meeting_date"));
			
			query.limit(10);
			
			List<Meeting> meetings = mongoTemplate.find(query, Meeting.class, "meetings");
			
			return meetings;
		} else return null;
	}

}
