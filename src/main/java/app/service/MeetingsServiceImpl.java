package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.dao.MeetingDAO;
import app.entity.Meeting;

@Service
public class MeetingsServiceImpl implements MeetingsService {
	
	@Autowired
	MeetingDAO meetingDAO;

	@Override
	@Transactional
	public List<Meeting> getMeetingsForUser(String username) {
		return meetingDAO.getMeetingsForUser(username);
	}

	@Override
	@Transactional
	public List<Meeting> getPastMeetingsForUser(String username) {
		return meetingDAO.getPastMeetingsForUser(username);
	}

	@Override
	@Transactional
	public List<Meeting> getFutureMeetingsForUser(String username) {
		return meetingDAO.getFutureMeetingsForUser(username);
	}

	@Override
	@Transactional
	public Meeting getMeeting(int id) {
		return meetingDAO.getMeeting(id);
	}

	@Override
	@Transactional
	public void saveMeeting(Meeting meeting) {
		meetingDAO.saveMeeting(meeting);
	}

}
