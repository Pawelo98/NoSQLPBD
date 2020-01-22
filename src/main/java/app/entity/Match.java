
package app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="matches")
//@Entity
//@Table(name="Matches")
public class Match {
	
	public enum Winner { One ("1"), Two ("2"), Zero ("0");
		
		private String name;
		
		Winner() {
	    }
	
	    Winner(String name) {
	        this.name = name;
	    }
	
	    public String getName() {
	        return this.name;
	    }    
    };

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="Id")
	private int match_id;
	
	//@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	//		CascadeType.DETACH, CascadeType.REFRESH})
	//@JoinColumn(name="Host")
	private int host;
	
	
	
	 //@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	//		 CascadeType.DETACH, CascadeType.REFRESH}) 
	 //@JoinColumn(name="Visitor")
	 private int visitor;
	 
	
	//@Column(name="Home_goals")
	private int home_goals;
	
	//@Column(name="Away_goals")
	private int away_goals;
	
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Temporal(TemporalType.DATE)
	//@Column(name="Game_date")
	private String game_date;
	
	//@Column(name="Winner")
	//@Enumerated(EnumType.STRING)
	private String winner;
	
	//@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
    //				CascadeType.DETACH, CascadeType.REFRESH})
	//@JoinColumn(name="League")
	private int league;
	
//	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//			CascadeType.DETACH, CascadeType.REFRESH,},fetch = FetchType.EAGER)
//	@JoinTable(name="refereeing",
//			joinColumns=@JoinColumn(name="Match_id"),
//			inverseJoinColumns=@JoinColumn(name="Referee_id"))
	private List<Referee> referees;
	
	public Match() {
		
	}

	public Match(int home_goals, int away_goals, String game_date, String winner) {
		
		this.home_goals = home_goals;
		this.away_goals = away_goals;
		this.game_date = game_date;
		this.winner = winner;
	}
	
	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}

	
	 public int getVisitor() { return visitor; }
	 
	 public void setVisitor(int visitor) { this.visitor = visitor; }
	 

	public int getHome_goals() {
		return home_goals;
	}

	public void setHome_goals(int home_goals) {
		this.home_goals = home_goals;
	}

	public int getAway_goals() {
		return away_goals;
	}

	public void setAway_goals(int away_goals) {
		this.away_goals = away_goals;
	}

	public String getGame_date() {
		return game_date;
	}

	public void setGame_date(String game_date) {
		this.game_date = game_date;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getLeague() {
		return league;
	}

	public void setLeague(int league) {
		this.league = league;
	}

	public List<Referee> getReferees() {
		return referees;
	}

	public void setReferees(List<Referee> referees) {
		this.referees = referees;
	}

	@Override
	public String toString() {
		return "Match [match_id=" + match_id  + ", away_goals="
				+ away_goals + ", game_date=" + game_date + ", winner=" + winner + "]";
	}
	
	public void addReferee(Referee theReferee) {
		if (referees==null) {
			referees = new ArrayList<>();
		}
		
		referees.add(theReferee);
	}

	
	

}
