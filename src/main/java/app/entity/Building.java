package app.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;	
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="buildings")
//@Entity
//@Table(name="Buildings")
public class Building {

	public enum Type { Stadium, Pitch, Training, Medical, Research, Warehouse };

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="Id")
	private int building_id;
	
	//@Column(name="Surface")
	private float surface;
	
	//@Column(name="Name")
	private String name;
	
	//@Column(name="Address")
	private String address;
	
	//@Column(name="Type")
	//@Enumerated(EnumType.STRING)
	private Type type;
	
	//@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	//		CascadeType.DETACH, CascadeType.REFRESH})
	//@JoinColumn(name="Club")
	private int club;
	
	//@OneToMany(mappedBy="building",
    //		cascade= {CascadeType.PERSIST, CascadeType.MERGE,
    //				CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Meeting> meetings;
	
	public Building() {
		
	}
	
	public Building(float surface, String name, String address, String type) {
		
		
		this.surface = surface;
		this.name = name;
		this.address = address;
		this.type = Type.valueOf(type);
	}

	

	public int getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(int building_id) {
		this.building_id = building_id;
	}

	public Set<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}

	public float getSurface() {
		return surface;
	}

	public void setSurface(float surface) {
		this.surface = surface;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	
	public int getClub() {
		return club;
	}

	public void setClub(int club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return "Building [building_id=" + building_id + ", surface=" + surface + ", name=" + name + ", address="
				+ address + ", type=" + type+"]";
	}

	public void addMeeting(Meeting tempMeeting) {
		if (meetings == null) {
			meetings = new HashSet<>();
		}
		meetings.add(tempMeeting);
		tempMeeting.setBuilding(this.getBuilding_id());
	}
	
}


