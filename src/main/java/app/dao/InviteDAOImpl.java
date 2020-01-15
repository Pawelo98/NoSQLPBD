package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.entity.Invite;
import app.entity.Meeting;

@Repository
public class InviteDAOImpl implements InviteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Invite> getInvitesForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Invite> query = session.createQuery("select invitetemp from Invite invitetemp "
				+ "where invitetemp.worker.username = :username "
				+ "order by invitetemp.invite_id"
				, Invite.class);
		
		query.setParameter("username", username);
		
		query.setFirstResult(0);
		query.setMaxResults(15);
		
		List<Invite> invites = query.list();
		
		return invites;
	}

	@Override
	public void saveInvite(Invite invite) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(invite);
	}

	@Override
	public List<Invite> getPastInvitesForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Invite> query = session.createQuery("select invitetemp from Invite invitetemp "
				+ "where invitetemp.worker.username = :username "
				+ "and invitetemp.meeting.meeting_date < current_date "
				+ "order by invitetemp.meeting.meeting_date desc"
				, Invite.class);
		
		query.setParameter("username", username);
		
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Invite> invites = query.list();
		
		return invites;
	}

	@Override
	public List<Invite> getFutureInvitesForUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Invite> query = session.createQuery("select invitetemp from Invite invitetemp "
				+ "where invitetemp.worker.username = :username "
				+ "and invitetemp.meeting.meeting_date > current_date "
				+ "order by invitetemp.meeting.meeting_date"
				, Invite.class);
		
		query.setParameter("username", username);
		
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Invite> invites = query.list();
		
		return invites;
	}

}
