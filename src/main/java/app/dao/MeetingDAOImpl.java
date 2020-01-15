package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.entity.Match;
import app.entity.Meeting;
import app.entity.User;

@Repository
public class MeetingDAOImpl implements MeetingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Meeting> getMeetingsForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Meeting> query = session.createQuery("select meetingtemp from Meeting meetingtemp "
				+ "where meetingtemp.initiator.username = :username "
				+ "order by meetingtemp.meeting_date"
				, Meeting.class);
		
		query.setParameter("username", username);
		
		query.setFirstResult(0);
		query.setMaxResults(15);
		
		List<Meeting> meetings = query.list();
		
		return meetings;
	}

	@Override
	public void saveMeeting(Meeting meeting) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(meeting);
	}

	@Override
	public List<Meeting> getPastMeetingsForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Meeting> query = session.createQuery("select meetingtemp from Meeting meetingtemp "
				+ "where meetingtemp.initiator.username = :username "
				+ "and meetingtemp.meeting_date < current_date "
				+ "order by meetingtemp.meeting_date desc"
				, Meeting.class);
		
		query.setParameter("username", username);
		
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Meeting> meetings = query.list();
		
		return meetings;
	}

	@Override
	public List<Meeting> getFutureMeetingsForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Meeting> query = session.createQuery("select meetingtemp from Meeting meetingtemp "
				+ "where meetingtemp.initiator.username = :username "
				+ "and meetingtemp.meeting_date > current_date "
				+ "order by meetingtemp.meeting_date"
				, Meeting.class);
		
		query.setParameter("username", username);
		
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Meeting> meetings = query.list();
		
		return meetings;
	}

	@Override
	public Meeting getMeeting(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Meeting meeting = session.get(Meeting.class, id);
		
		return meeting;
	}

}
