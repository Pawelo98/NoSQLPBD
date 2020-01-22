package app.service;

import java.util.List;

import app.entity.Invite;
import app.entity.Meeting;

public interface InvitesService {
	public List<Invite> getInvitesForUser(String username);
	public List<Meeting> getPastInvitesForUser(String username);
	public List<Meeting> getFutureInvitesForUser(String username);
	public void saveInvite(Invite invite);
}
