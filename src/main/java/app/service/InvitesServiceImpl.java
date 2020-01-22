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
import app.entity.User;

@Service
public class InvitesServiceImpl implements InvitesService {

	@Autowired
	InviteDAO inviteDAO;
	
	@Autowired
	MeetingDAO meetingDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	@Transactional
	public List<Meeting> getInvitesForUser(String username) {
		User user = userDAO.getUser(username);
		
		return meetingDAO.getInvitesForUser(user);
	}

	@Override
	@Transactional
	public List<Meeting> getPastInvitesForUser(String username) {
		//return inviteDAO.getPastInvitesForUser(username);
		User user = userDAO.getUser(username);
		
		return meetingDAO.getPastInvitesForUser(user);
	}

	@Override
	@Transactional
	public List<Meeting> getFutureInvitesForUser(String username) {
		//return inviteDAO.getFutureInvitesForUser(username);
		
		User user = userDAO.getUser(username);
		return meetingDAO.getFutureInvitesForUser(user);
	}

	@Override
	@Transactional
	public void saveInvite(Invite invite) {
		inviteDAO.saveInvite(invite);
	}

}
