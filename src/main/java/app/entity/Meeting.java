package app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
@Document(collection="meetings")
//@Entity
//@Table(name="Meetings")
public class Meeting {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="Id")
	private int meeting_id;
	
	//@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	//		CascadeType.DETACH, CascadeType.REFRESH})
	//@JoinColumn(name="Building")
	private int building;
	
	//@Column(name="Room")
	private String room;
	
	//@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	//		CascadeType.DETACH, CascadeType.REFRESH})
	//@JoinColumn(name="Initiator")
	private String initiator;
	
	//@Column(name="Estimated_length")
	private float estimated_length;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Temporal(TemporalType.DATE)
	//@Column(name="Meeting_date")
	private Date meeting_date;
					
	//@OneToMany(mappedBy="meeting",
    		//cascade= {CascadeType.PERSIST, CascadeType.MERGE,
    		//		CascadeType.DETACH, CascadeType.REFRESH})
    private List<Invite> invites;

	public Meeting() {
		
	}

	public Meeting(int building, String initiator, int meeting_id, String room, float estimated_length, Date meeting_date) {
		this.meeting_id = meeting_id;
		this.initiator = initiator;
		this.building = building;
		this.room = room;
		this.estimated_length = estimated_length;
		this.meeting_date = meeting_date;
	}
	
	public Meeting(int building, String initiator, String room, float estimated_length, Date meeting_date) {
		this.initiator = initiator;
		this.building = building;
		this.room = room;
		this.estimated_length = estimated_length;
		this.meeting_date = meeting_date;
	}
	
	public Meeting(String room, float estimated_length, Date meeting_date) {
		
		this.room = room;
		this.estimated_length = estimated_length;
		this.meeting_date = meeting_date;
	}
	
	public int getMeeting_id() {
		return meeting_id;
	}

	public void setMeeting_id(int meeting_id) {
		this.meeting_id = meeting_id;
	}

	public int getBuilding() {
		return building;
	}

	public void setBuilding(int building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public float getEstimated_length() {
		return estimated_length;
	}

	public void setEstimated_length(float estimated_length) {
		this.estimated_length = estimated_length;
	}

	public Date getMeeting_date() {
		return meeting_date;
	}

	public void setMeeting_date(Date meeting_date) {
		this.meeting_date = meeting_date;
	}
	
	public List<Invite> getInvites() {
		return invites;
	}

	public void setInvites(List<Invite> invites) {
		this.invites = invites;
	}

	@Override
	public String toString() {
		return "Meeting [meeting_id=" + meeting_id + ", room=" + room + ", estimated_length=" + estimated_length + ", meeting_date=" + meeting_date +  "]";
	}

	public void addInvite(Invite tempInvite) {
		if (invites == null) {
			invites = new ArrayList<>();
		}
		invites.add(tempInvite);
		tempInvite.setMeeting(this.getMeeting_id());
	}
	
}
