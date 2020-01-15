package app.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.dao.LeagueDAO;
import app.entity.Building;
import app.entity.Club;
import app.entity.League;
import app.entity.Match;
import app.entity.User;
import app.entity.Referee;
import app.entity.Worker;
import app.service.BuildingService;
import app.service.ClubService;
import app.service.LeagueService;
import app.service.MatchService;
import app.service.RefereeService;
import app.service.UserService;
import app.service.WorkerService;

@Controller
@RequestMapping("/clubs")
public class ClubController {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private WorkerService workerService;

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RefereeService refereeService;

	@GetMapping("/buildings")
	public String listBuildings(@RequestParam("selected") int theId, Model theModel) {
		
		List<Building> building = buildingService.getBuildings(theId);
		theModel.addAttribute("buildingFromDB", building);
		

		List<Club> club = clubService.getClubs();
		theModel.addAttribute("clubss", club);

		return "buildings-view";
	}
	
	@GetMapping("/buildingsManagement")
	public String showBuildings(Model model) {
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("clubss", club);
		return "buildingsManagement-view";
	}
	

	@GetMapping("/workers")
	public String listWorkers(@RequestParam("selected") int theId, Model model) {
		List<Worker> worker = workerService.getWorkers(theId);
		model.addAttribute("worker", worker);

		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		//int klubik =clubService.getClub
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User curr = userService.getUser(username);
		
		if(curr.getClub().getClub_id()==theId)
			return "workers-view";
		else 
			return "workers-no-pay-view";
		
		
		
	}
	
	@GetMapping("/workersPayoff")
	public String listWorkersWithPayoff(@RequestParam("clubId") int theId, Model model) {
		List<Worker> worker = workerService.getWorkers(theId);
		model.addAttribute("worker", worker);
		
		String salary = workerService.getSumOfSalary(theId);
		model.addAttribute("salary", salary);

		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		return "workers-payoff-view";
		
	}

	
	@GetMapping("/workersManagement")
	public String showWorkers(Model model) {
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("clubss", club);
		return "workersManagement-view";
	}
	
	
	@GetMapping("/matches")
	public String listMatches(@RequestParam("selected") int theId, Model model) {
		List<Match> matchPast = matchService.getPastMatchesForClub(theId);
		model.addAttribute("matchesPast", matchPast);
		
		List<Match> matchFuture = matchService.getFutureMatchesForClub(theId);
		model.addAttribute("matchesFuture", matchFuture);
		
		List<Referee> allReferees = refereeService.getReferees();

		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		

		List<Referee> matchReferees = new ArrayList<Referee>();
		matchReferees.clear();
		
		for(int i=0; i<matchPast.size();i++) {
			
			if (matchPast.get(i).getReferees().isEmpty())
						matchReferees.add(allReferees.get(0));
			else
				matchReferees.add(matchPast.get(i).getReferees().get(0));
		}

		model.addAttribute("refjsp", matchReferees);
		
		List<Referee> matchReferees2 = new ArrayList<Referee>();
		matchReferees2.clear();
		
		for(int i=0; i<matchFuture.size();i++) {
			
			if (matchFuture.get(i).getReferees().isEmpty())
						matchReferees2.add(allReferees.get(0));
			else
				matchReferees2.add(matchFuture.get(i).getReferees().get(0));
		}
		model.addAttribute("refjsp2", matchReferees2);

		return "matches-for-club-view";
	}
	
	
	@GetMapping("/matchesManagement")
	public String showMatches(Model model) {

		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);

		return "matches-for-clubManagement-view";
	}

	@GetMapping("/showFormForAddBuilding")
	public String showFormForAddBuilding(Model model) {
		Building theBuilding = new Building();
		model.addAttribute("building", theBuilding);
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);

		return "building-add-form";

	}

	@GetMapping("/showFormForUpdateBuilding")
	public String showFormForUpdateBuilding(@RequestParam("buildingId") int theId, Model theModel) {
		Building building = buildingService.getBuilding(theId);
		theModel.addAttribute("building", building);
		
		List<Club> club = clubService.getClubs();
		theModel.addAttribute("club", club);

		return "building-add-form";

	}

	@PostMapping("/saveBuilding")
	public String saveBuilding(@ModelAttribute("building") Building theBuilding) {
		buildingService.saveBuilding(theBuilding);
		// jak z enumami, z listy??
		// jak z klubami?? nazwy, a nie id
		return "redirect:/clubs/buildingsManagement";

	}
	
	@GetMapping("/deleteBuilding")
	public String saveWorker(@RequestParam("buildingId") int theId) {
		buildingService.deleteBuilding(theId);
		return "redirect:/clubs/buildingsManagement";

	}

	@GetMapping("/showFormForAddWorker")
	public String showFormForAddWorker(Model model){
		Worker theWorker = new Worker();
		model.addAttribute("worker", theWorker);
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		
		return "worker-add-form";
	}

	@GetMapping("/showFormForUpdateWorker")
	public String showFormForUpdateWorker(@RequestParam("workerId") int theId, Model theModel) {
		Worker worker = workerService.getWorker(theId);
		theModel.addAttribute("worker", worker);
		
		List<Club> club = clubService.getClubs();
		theModel.addAttribute("club", club);

		return "worker-add-form";

	}

	@PostMapping("/saveWorker")
	public String saveWorker(@ModelAttribute("worker") Worker theWorker) {
		workerService.saveWorker(theWorker);
		
		return "redirect:/clubs/workersManagement";

	}
	
	@GetMapping("/deleteWorker")
	public String deleteWorker(@RequestParam("workerId") int theId) {
			workerService.deleteWorker(theId);
			return "redirect:/clubs/workersManagement";

	}
	
	@GetMapping("/accessDenied")
	public String showAccessDenied() {

		return "access-denied";

	}
	
	@PostMapping("/saveMatch")
	public String saveMatch(@ModelAttribute("match") Match theMatch, 
			@RequestParam("sedzia1") int ref1, @RequestParam("sedzia2") int ref2, @RequestParam("sedzia3") int ref3) {
		Referee  refer1 = refereeService.getReferee(ref1);
		Referee  refer2 = refereeService.getReferee(ref2);
		Referee  refer3 = refereeService.getReferee(ref3);
		theMatch.addReferee(refer1);
		theMatch.addReferee(refer2);
		theMatch.addReferee(refer3);
		matchService.saveMatch(theMatch);
		
		return "redirect:/clubs/matchesManagement";

	}
	
	@GetMapping("/showFormForAddMatch")
	public String showFormForAddMatch(Model model){
		Match match = new Match();
		model.addAttribute("match", match);
		
		List<League> leagues = leagueService.getLeagues();
		model.addAttribute("leagues", leagues);
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		
		List<Referee> referee = refereeService.getReferees();
		model.addAttribute("ref", referee);
		
		return "match-for-club-add-form";
	}
	
	@GetMapping("/showFormForUpdateMatch")
	public String showFormForUpdateMatch(@RequestParam("matchId") int theId, Model theModel) {
		Match match = matchService.getMatch(theId);
		theModel.addAttribute("match", match);
		
		List<League> leagues = leagueService.getLeagues();
		theModel.addAttribute("leagues", leagues);
		
		List<Club> club = clubService.getClubs();
		theModel.addAttribute("club", club);
		
		List<Referee> referee = refereeService.getReferees();
		theModel.addAttribute("ref", referee);
		
		List<Referee> mtchReferees = match.getReferees();
		String sedzia = "mRef";
		String number;
		for(int i=0; i<mtchReferees.size();i++) {
			number = String.valueOf(i+1);
			sedzia = sedzia+number;
			theModel.addAttribute(sedzia, mtchReferees.get(i));
			sedzia="mRef";
		}
		

		return "match-for-club-add-form";

	}
	
	@GetMapping("/deleteMatch")
	public String deleteMatch(@RequestParam("matchId") int theId) {
		matchService.deleteMatch(theId);
			return "redirect:/clubs/matchesManagement";

	}
}
