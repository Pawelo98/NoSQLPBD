package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.InviteDAO;
import app.dao.MeetingDAO;
import app.dao.UserDAO;
import app.entity.Invite;
import app.entity.Meeting;

@Service
public class InvitesServiceImpl implements InvitesService {

	@Autowired
	InviteDAO inviteDAO;
	
	@Autowired
	MeetingDAO meetingDAO;
	
	@Override
	@Transactional
	public List<Invite> getInvitesForUser(String username) {
		return inviteDAO.getInvitesForUser(username);
	}

	@Override
	@Transactional
	public List<Meeting> getPastInvitesForUser(String username) {
		//return inviteDAO.getPastInvitesForUser(username);
		return meetingDAO.getPastInvitesForUser(username);
	}

	@Override
	@Transactional
	public List<Meeting> getFutureInvitesForUser(String username) {
		//return inviteDAO.getFutureInvitesForUser(username);
		return meetingDAO.getFutureInvitesForUser(username);
	}

	@Override
	@Transactional
	public void saveInvite(Invite invite) {
		inviteDAO.saveInvite(invite);
	}

}
