package app.service;

import java.util.List;

import app.entity.Meeting;

public interface MeetingsService {
	public List<Meeting> getMeetingsForUser(String username);
	public List<Meeting> getPastMeetingsForUser(String username);
	public List<Meeting> getFutureMeetingsForUser(String username);
	public Meeting getMeeting(int id);
	public void saveMeeting(Meeting meeting);
}
