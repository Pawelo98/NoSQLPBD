package app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
//@Entity
//@Table(name="Users")
public class User {

		@Id
		//@Column(name="username")
		private String username;
			
		//@Column(name="password")
		private String password;
		
		//@Column(name="enabled")
		private int enabled;
	
		//@DateTimeFormat(pattern = "yyyy-MM-dd")
		//@Temporal(TemporalType.DATE)
		//@Column(name="Registration_date")
		private Date registrationDate;
		
		//@Column(name="Name")
		private String name;
		
		//@Column(name="Surname")
		private String surname;
		
		//@Column(name="Address")
		private String address;
		
		//@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
		//		CascadeType.DETACH, CascadeType.REFRESH})
		//@JoinColumn(name="Club")
		private Club club;
		
		//@OneToMany(mappedBy = "id.user", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
		//		CascadeType.REFRESH}, fetch = FetchType.EAGER)
		private Set<Authority> authorities;
		
		//@OneToMany(mappedBy="worker",
	    //		cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	    //				CascadeType.DETACH, CascadeType.REFRESH})
	    private Set<Invite> invites;
		
		//@OneToMany(mappedBy="initiator",
	    //		cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	    //				CascadeType.DETACH, CascadeType.REFRESH})
	    private Set<Meeting> meetings;
		
		public User() {
			
		}
		
		public User(String password, int enabled, Date registrationDate, String name, String surname,
				String address) {
			
			this.password = password;
			this.enabled = enabled;
			this.registrationDate = registrationDate;
			this.name = name;
			this.surname = surname;
			this.address = address;
		}


		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getEnabled() {
			return enabled;
		}

		public void setEnabled(int enabled) {
			this.enabled = enabled;
		}

		public Date getRegistrationDate() {
			return registrationDate;
		}

		public void setRegistrationDate(Date registrationDate) {
			this.registrationDate = registrationDate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Club getClub() {
			return club;
		}

		public void setClub(Club club) {
			this.club = club;
		}

		public Set<Authority> getAuthorities() {
			return authorities;
		}

		public void setAuthorities(Set<Authority> authorities) {
			this.authorities = authorities;
		}
		
		public Set<Meeting> getMeetings() {
			return meetings;
		}

		public void setMeetings(Set<Meeting> meetings) {
			this.meetings = meetings;
		}

		public Set<Invite> getInvites() {
			return invites;
		}

		public void setInvites(Set<Invite> invites) {
			this.invites = invites;
		}

		@Override
		public String toString() {
			return "User [username=" + username + ", password=" + password + "]";
		}

		public void addAuthority(Authority tempAuthority) {
			if (authorities == null) {
				authorities = new HashSet<>();
			}
			authorities.add(tempAuthority);
		}
		
		public void addMeeting(Meeting tempMeeting) {
			if (meetings == null) {
				meetings = new HashSet<>();
			}
			meetings.add(tempMeeting);
			tempMeeting.setInitiator(this);
		}
		
		public void addInvite(Invite tempInvite) {
			if (invites == null) {
				invites = new HashSet<>();
			}
			invites.add(tempInvite);
			tempInvite.setWorker(this);
		}

		
}
