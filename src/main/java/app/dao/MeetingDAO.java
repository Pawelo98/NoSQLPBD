package app.dao;

import java.util.List;

import app.entity.Invite;
import app.entity.Meeting;

public interface MeetingDAO {

	public List<Meeting> getMeetingsForUser(String username);
	
	public List<Meeting> getPastMeetingsForUser(String username);
	
	public List<Meeting> getFutureMeetingsForUser(String username);

	public void saveMeeting(Meeting meeting);
	
	public Meeting getMeeting(int id);

	void addMeeting(Meeting meeting);

	public List<Meeting> getPastInvitesForUser(String username);

	public List<Meeting> getFutureInvitesForUser(String username);
}
