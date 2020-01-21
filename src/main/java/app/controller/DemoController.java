package app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.Referee;
import app.service.RefereeService;
import app.dao.RefereeDAOImpl;
import app.entity.Building;
import app.entity.Club;
import app.entity.Invite;
import app.entity.League;
import app.entity.Match;
import app.entity.Meeting;
import app.entity.Referee;
import app.entity.User;
import app.service.BuildingService;
import app.service.ClubService;
import app.service.InvitesService;
import app.service.LeagueService;
import app.service.MatchService;
import app.service.MatchesService;
import app.service.MeetingsService;
import app.service.RefereeService;
import app.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	MatchesService matchesService;
	
	@Autowired
	MeetingsService meetingsService;
	
	@Autowired
	InvitesService invitesService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	UserService userService;

	@Autowired
	RefereeService refereeService;
	
	@Autowired
	LeagueService leagueService;
	
	@Autowired
	ClubService clubService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	RefereeDAOImpl refereDAOImpl;
	
	@GetMapping("/")
	public String showHome(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User curr = userService.getUser(username);
		//List<Match> matches = matchesService.getPastMatches(curr.getClub().getClub_id());
		List<Match> matches = new ArrayList<Match>();
		//List<Match> matchesFuture = matchesService.getFutureMatches(curr.getClub().getClub_id());
		List<Match> matchesFuture = new ArrayList<Match>();
		
		List<Meeting> meetingsPast = meetingsService.getPastMeetingsForUser(username);
		List<Invite> invitesPast = invitesService.getPastInvitesForUser(username);
		
		model.addAttribute("meetingsPast", meetingsPast);
		model.addAttribute("invitesPast", invitesPast);
		
		List<Meeting> meetingsFuture = meetingsService.getFutureMeetingsForUser(username);
		List<Invite> invitesFuture = invitesService.getFutureInvitesForUser(username);
		
		model.addAttribute("meetingsFuture", meetingsFuture);
		model.addAttribute("invitesFuture", invitesFuture);
		
		model.addAttribute("matchesDemo", matches);
		model.addAttribute("matchesFuture", matchesFuture);
		
		return "home-page";
	}
	
	@GetMapping("/matches")
	public String showClubBuildings(Model model) {
		//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String username = ((User)principal).getUsername();
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User curr = userService.getUser(username);
		List<Building> buildings = buildingService.getBuildings(curr.getClub());
		
		model.addAttribute("buildings", buildings);
		
		
		return "matches-view";
	}
	
	
	@GetMapping("/meetings")
	public String showMeetings(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		List<Meeting> meetings = meetingsService.getMeetingsForUser(username);
		List<Invite> invites = invitesService.getInvitesForUser(username);
		
		model.addAttribute("meetings", meetings);
		model.addAttribute("invites", invites);
		return "meetings-view";
	}
	
	@GetMapping("/clubs")
	public String showClubs(Model model) {
		return "clubs-view";
	}
	
	
	@GetMapping("/leagues")
	public String listLeagues(Model model)
	{
		List<League> league = leagueService.getLeagues();
		//List<League> league = leagueService.getLeaguesMongo();
		model.addAttribute("league", league);
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
				
		return "leagues-view";
	}
	
	@GetMapping("/leagues/matches")
	public String listMatchesForLeague(Model model)
	{
		List<Match> match = matchesService.getMatchesForDate();
		model.addAttribute("match", match);
				
		return "matches-for-league-view";
	}
	
	@GetMapping("/leagues-table")
	public String leagueTable(@RequestParam("selected") int theId, Model theModel) {
		
		List<Object[]> clubHomeGoals = clubService.getClubsHomeGoals(theId);
		List<Object[]> clubAwayGoals = clubService.getClubsAwayGoals(theId);
		List<Object[]> clubHomeGoalsConceded = clubService.getClubsHomeGoalsConceded(theId);
		List<Object[]> clubAwayGoalsConceded = clubService.getClubsAwayGoalsConceded(theId);
		List<Long> clubHomePoints = clubService.getClubsHomePoints(theId);
		List<Long> clubAwayPoints = clubService.getClubsAwayPoints(theId);
		List<Object[]> clubFromDB3 = new ArrayList<Object[]>();
		
		for(int i=0; i<clubHomeGoals.size(); i++) {
			Object[] temp = {clubHomeGoals.get(i)[0], (Object)((long)clubHomeGoals.get(i)[1] + 
					(long)clubAwayGoals.get(i)[1]), (Object)((long)clubHomeGoalsConceded.get(i)[1] + (long)clubAwayGoalsConceded.get(i)[1]),
					(long)clubHomeGoals.get(i)[2] + (long)clubAwayGoals.get(i)[2], clubHomePoints.get(i) + clubAwayPoints.get(i)};
			clubFromDB3.add(temp);
		}
		
		 int i, j;
		 Object [] temp;
	        boolean swapped; 
	        for (i = 0; i < clubFromDB3.size() - 1; i++)  
	        { 
	            swapped = false; 
	            for (j = 0; j < clubFromDB3.size() - i - 1; j++)  
	            { 
	                if ((long)clubFromDB3.get(j)[4] < (long)clubFromDB3.get(j+1)[4])  
	                { 
	                    
	                    temp = clubFromDB3.get(j); 
	                    clubFromDB3.set(j, clubFromDB3.get(j+1)); 
	                    clubFromDB3.set(j+1,temp);
	                    swapped = true; 
	                } 
	            } 
	  
	            if (swapped == false) 
	                break; 
	        } 
		
	        List<Object[]> leagueTable = new ArrayList<Object[]>();
	        
	        //dodanie miejsc dla klub�w
	        for (int k = 0; k < clubFromDB3.size(); k++) {
	        	Object [] tempLT = {k+1 + ".",clubFromDB3.get(k)[0],clubFromDB3.get(k)[1],clubFromDB3.get(k)[2],clubFromDB3.get(k)[3],clubFromDB3.get(k)[4]};
	        	leagueTable.add(tempLT);
	        }
		
		theModel.addAttribute("clubFromDB", leagueTable);

		List<League> league = leagueService.getLeagues();
		theModel.addAttribute("league", league);

		return "leagues-table-view";
	}
	

	@GetMapping("/showUser")
	public String showUser(@RequestParam("username") String username, Model model) {
		
		User user = userService.getUser(username);
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		
		model.addAttribute("user", user);
		
		return "user-view";
	}
	
	@PostMapping("/saveCredentials")
	public String saveCredentials(@ModelAttribute("user") User user)
	{
		String pass = user.getPassword();
		if(!pass.substring(0, 7).equals("{bcrypt}")) {
			String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
			user.setPassword("{bcrypt}" + hashedPass);
		}
		
		user.setEnabled(1);
		
		userService.saveUser(user);
		
		return "redirect:/";
	}
	
	@GetMapping("/admins")
	public String showAdmins(Model model) {
		List<User> users = userService.getUsers();
		
		model.addAttribute("users", users);
		
		return "admins-view";
	}
	
	@GetMapping("/referees")
	public String listReferees(Model theModel) {
		
		List<Referee> referees = refereeService.getRefereesMongo();
//		List<Referee> referees = refereDAOImpl.findAll();
		
		theModel.addAttribute("referees", referees);
		
		return "referees-view";
	}
	
	@PostMapping("/saveReferee")
	public String saveReferee(@ModelAttribute("refereeId") Referee referee){

		refereeService.saveReferee(referee);
		
	return "redirect:/referees";
	}
	
	@GetMapping("/showFormForAddReferee")
	public String showFormForAddReferee(Model theModel) {
		
		Referee referee = new Referee();
		theModel.addAttribute("referee", referee);
		
		String[] nationality = {"Afghanistan", "Albania", "Algeria", "AmericanSamoa", "Andorra", "Angola", "Anguilla",
		        "Antarctica", "AntiguaandBarbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
		        "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
		        "BosniaandHerzegowina", "Botswana", "BouvetIsland", "Brazil", "BritishIndianOceanTerritory", "BruneiDarussalam",
		        "Bulgaria", "BurkinaFaso", "Burundi", "Cambodia", "Cameroon", "Canada", "CapeVerde", "CaymanIslands",
		        "CentralAfricanRepublic" , "Chad", "Chile", "China", "ChristmasIsland" , "Cocos" , "Colombia",
		        "Comoros", "Congo", "CongoDR" , "CookIslands", "CostaRica", "CotedIvore",
		        "Croatia", "Cuba", "Cyprus", "CzechRepublic", "Denmark", "Djibouti", "Dominica", "DominicanRepublic",
		        "EastTimor", "Ecuador", "Egypt", "ElSalvador", "EquatorialGuinea", "Eritrea", "Estonia", "Ethiopia",
		        "FalklandIslands", "FaroeIslands", "Fiji", "Finland", "France", "FranceMetropolitan", "FrenchGuiana",
		        "FrenchPolynesia", "FrenchSouthernTerritories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
		        "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "GuineaBissau", "Guyana", "Haiti",
		        "HeardandMcDonaldIslands", "HolySee", "Honduras", "HongKong", "Hungary", "Iceland", "India",
		        "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
		        "Kazakhstan", "Kenya", "Kiribati", "Korea", "KoreaDPR", "Kuwait",
		        "Kyrgyzstan", "Lao", "Latvia", "Lebanon", "Lesotho", "Liberia", "LibyanArabJamahiriya",
		        "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar",
		        "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "MarshallIslands", "Martinique", "Mauritania", "Mauritius",
		        "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat",
		        "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "NetherlandsAntilles",
		        "NewCaledonia", "NewZealand", "Nicaragua", "Niger", "Nigeria", "Niue", "NorfolkIsland", "NorthernMarianaIslands",
		        "Norway", "Oman", "Pakistan", "Palau", "Panama", "PapuaNewGuinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
		        "Poland", "Portugal", "PuertoRico", "Qatar", "Reunion", "Romania", "RussianFederation", "Rwanda",
		        "SaintKittsandNevis", "SaintLucia", "SaintVincentandtheGrenadines", "Samoa", "SanMarino",
		        "SaoTomeandPrincipe", "SaudiArabia", "Senegal", "Seychelles", "SierraLeone", "Singapore",
		        "Slovakia", "Slovenia", "SolomonIslands", "Somalia", "SouthAfrica",
		        "SouthGeorgiaandtheSouthSandwichIslands", "Spain", "SriLanka", "StHelena", "StPierreandMiquelon",
		        "Sudan", "Suriname", "SvalbardandJanMayenIslands", "Swaziland", "Sweden", "Switzerland", "SyrianArabRepublic",
		        "Taiwan", "ProvinceofChina", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga",
		        "TrinidadandTobago", "Tunisia", "T�rkiye", "Turkmenistan", "TurksandCaicosIslands", "Tuvalu", "Uganda", "Ukraine",
		        "UnitedArabEmirates", "UnitedKingdom", "UnitedStates", "UnitedStatesMinorOutlyingIslands", "Uruguay",
		        "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "VirginIslandsBr", "VirginIslandsUS",
		        "WallisandFutunaIslands", "WesternSahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
		};
		
		theModel.addAttribute("nationality", nationality);
		
		return "referee-form";
	}
	
	@GetMapping("/showFormForRefereeUpdate")
	public String showFormForRefereeUpdate(@RequestParam("refereeId") int theId, Model theModel) {
		
		Referee referee = refereeService.getReferee(theId);
		theModel.addAttribute("referee", referee);
		
		Object[] nationality = {"Afghanistan", "Albania", "Algeria", "AmericanSamoa", "Andorra", "Angola", "Anguilla",
		        "Antarctica", "AntiguaandBarbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
		        "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
		        "BosniaandHerzegowina", "Botswana", "BouvetIsland", "Brazil", "BritishIndianOceanTerritory", "BruneiDarussalam",
		        "Bulgaria", "BurkinaFaso", "Burundi", "Cambodia", "Cameroon", "Canada", "CapeVerde", "CaymanIslands",
		        "CentralAfricanRepublic" , "Chad", "Chile", "China", "ChristmasIsland" , "Cocos" , "Colombia",
		        "Comoros", "Congo", "CongoDR" , "CookIslands", "CostaRica", "CotedIvore",
		        "Croatia", "Cuba", "Cyprus", "CzechRepublic", "Denmark", "Djibouti", "Dominica", "DominicanRepublic",
		        "EastTimor", "Ecuador", "Egypt", "ElSalvador", "EquatorialGuinea", "Eritrea", "Estonia", "Ethiopia",
		        "FalklandIslands", "FaroeIslands", "Fiji", "Finland", "France", "FranceMetropolitan", "FrenchGuiana",
		        "FrenchPolynesia", "FrenchSouthernTerritories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
		        "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "GuineaBissau", "Guyana", "Haiti",
		        "HeardandMcDonaldIslands", "HolySee", "Honduras", "HongKong", "Hungary", "Iceland", "India",
		        "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
		        "Kazakhstan", "Kenya", "Kiribati", "Korea", "KoreaDPR", "Kuwait",
		        "Kyrgyzstan", "Lao", "Latvia", "Lebanon", "Lesotho", "Liberia", "LibyanArabJamahiriya",
		        "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar",
		        "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "MarshallIslands", "Martinique", "Mauritania", "Mauritius",
		        "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat",
		        "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "NetherlandsAntilles",
		        "NewCaledonia", "NewZealand", "Nicaragua", "Niger", "Nigeria", "Niue", "NorfolkIsland", "NorthernMarianaIslands",
		        "Norway", "Oman", "Pakistan", "Palau", "Panama", "PapuaNewGuinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
		        "Poland", "Portugal", "PuertoRico", "Qatar", "Reunion", "Romania", "RussianFederation", "Rwanda",
		        "SaintKittsandNevis", "SaintLucia", "SaintVincentandtheGrenadines", "Samoa", "SanMarino",
		        "SaoTomeandPrincipe", "SaudiArabia", "Senegal", "Seychelles", "SierraLeone", "Singapore",
		        "Slovakia", "Slovenia", "SolomonIslands", "Somalia", "SouthAfrica",
		        "SouthGeorgiaandtheSouthSandwichIslands", "Spain", "SriLanka", "StHelena", "StPierreandMiquelon",
		        "Sudan", "Suriname", "SvalbardandJanMayenIslands", "Swaziland", "Sweden", "Switzerland", "SyrianArabRepublic",
		        "Taiwan", "ProvinceofChina", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga",
		        "TrinidadandTobago", "Tunisia", "T�rkiye", "Turkmenistan", "TurksandCaicosIslands", "Tuvalu", "Uganda", "Ukraine",
		        "UnitedArabEmirates", "UnitedKingdom", "UnitedStates", "UnitedStatesMinorOutlyingIslands", "Uruguay",
		        "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "VirginIslandsBr", "VirginIslandsUS",
		        "WallisandFutunaIslands", "WesternSahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
		};
		
		theModel.addAttribute("nationality", nationality);
		
		return "referee-form";
	}
	
	@GetMapping("/deleteReferee")
	public String deleteReferee(@RequestParam("refereeId") int theId) {
	
		refereeService.deleteReferee(theId);
		
		return "redirect:/referees";
	}
}
