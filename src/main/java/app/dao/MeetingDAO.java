package app.dao;

import java.util.List;

import app.entity.Invite;
import app.entity.Meeting;
import app.entity.User;

public interface MeetingDAO {

	public List<Meeting> getMeetingsForUser(String username);
	
	public List<Meeting> getPastMeetingsForUser(String username);
	
	public List<Meeting> getFutureMeetingsForUser(String username);

	public void saveMeeting(Meeting meeting);
	
	public Meeting getMeeting(int id);

	void addMeeting(Meeting meeting);

	public List<Meeting> getPastInvitesForUser(User user);

	public List<Meeting> getFutureInvitesForUser(User user);

	public List<Meeting> getInvitesForUser(User user);
}
