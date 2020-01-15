package app.service;

import java.util.List;

import app.entity.Invite;

public interface InvitesService {
	public List<Invite> getInvitesForUser(String username);
	public List<Invite> getPastInvitesForUser(String username);
	public List<Invite> getFutureInvitesForUser(String username);
	public void saveInvite(Invite invite);
}
