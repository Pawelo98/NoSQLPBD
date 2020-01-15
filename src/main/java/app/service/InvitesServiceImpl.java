package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.InviteDAO;
import app.entity.Invite;

@Service
public class InvitesServiceImpl implements InvitesService {

	@Autowired
	InviteDAO inviteDAO;
	
	@Override
	@Transactional
	public List<Invite> getInvitesForUser(String username) {
		return inviteDAO.getInvitesForUser(username);
	}

	@Override
	@Transactional
	public List<Invite> getPastInvitesForUser(String username) {
		return inviteDAO.getPastInvitesForUser(username);
	}

	@Override
	@Transactional
	public List<Invite> getFutureInvitesForUser(String username) {
		return inviteDAO.getFutureInvitesForUser(username);
	}

	@Override
	@Transactional
	public void saveInvite(Invite invite) {
		inviteDAO.saveInvite(invite);
	}

}
