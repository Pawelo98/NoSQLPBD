package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.javafaker.Faker;

import app.entity.Authority;
import app.entity.Building;
import app.entity.Club;
import app.entity.League;
import app.entity.Match;
import app.entity.Referee;
import app.entity.User;
import app.entity.Worker;
import app.entity.Building.Type;
import app.service.AuthorityService;
import app.service.BuildingService;
import app.service.ClubService;
import app.service.RefereeService;
import app.service.UserService;
import app.service.WorkerService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RefereeService refereeService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping("/addUser")
	public String addUser(Model model)
	{
		User user = new User();
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		
		model.addAttribute("user", user);
		
		return "add-user-view";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user)
	{
		user.setEnabled(1);
		user.setRegistrationDate(new Date());
		
		String pass = user.getPassword();
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		user.setPassword("{bcrypt}" + hashedPass);
		Authority auth = new Authority(user, "ROLE_PHYSICAL");
		
		user.addAuthority(auth);
		userService.saveUser(user);
		authorityService.saveAuthority(auth);
		
		return "redirect:/admins";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user)
	{
		String pass = user.getPassword();
		if(pass.length()>=8) {
			String passFirst = pass.substring(0, 8);
			if(!passFirst.equals("{bcrypt}")) {
				String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
				user.setPassword("{bcrypt}" + hashedPass);
			}
		} else {
			String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
			user.setPassword("{bcrypt}" + hashedPass);
		}
		
		user.setEnabled(1);
		
		userService.saveUser(user);
		
		return "redirect:/admins";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username)
	{
		userService.deleteUser(username);
		
		return "redirect:/admins";
	}
	
	@GetMapping("/editUser")
	public String editUser(@RequestParam("username") String username, Model model)
	{
		User user = userService.getUser(username);
		
		List<Club> club = clubService.getClubs();
		model.addAttribute("club", club);
		
		model.addAttribute("user", user);
		
		return "edit-user-view";
	}
	
	@GetMapping("/list")
	public String listCustomers(Model model)
	{
		List<League> users = userService.getLeagues();
		
		model.addAttribute("users", users);
		
		return "list-users";
	}
	
	@PostMapping("/list")
	public String saveUsers()
	{
		Random generator = new Random();
		
		// dodawanie s�dzi�w
		for(int i=0; i<20; i++)
		{
			String[] countryCodes = Locale.getISOCountries();
			Locale gb = new Locale("en-GB");
			Locale locale = new Locale("", countryCodes[generator.nextInt(countryCodes.length)]);
			
			String nationality = locale.getDisplayCountry(gb);
			Faker faker = new Faker(locale);
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			
			Referee ref = new Referee(name, surname, nationality);
			refereeService.saveReferee(ref);
		}
		
		// dodawanie klub�w
		for(int i=0; i<34; i++)
		{
			Faker fakerZwykly = new Faker();
			Locale gb = new Locale("de-DE");
			Locale pl = new Locale("pl-PL");
			
			Faker faker;
			String nationality;
			if(i<18) {
				faker = new Faker(gb);
				nationality = "Germany";
			} else {
				faker = new Faker(pl);
				nationality = "Poland";
			}
				
			String name = faker.team().name();
			String shirtHome = faker.color().name();
			String shirtAway = faker.color().name();
			
			Club club = new Club(name, nationality, shirtHome, shirtAway);
			clubService.saveClub(club);
			
			// dodawanie u�ytkownik�w
			if(i==0)
			{
				for(int j=0; j<3; j++) {
					String login = "";
					String password = "";
					String role = "";
					if(j==0)
					{
						login = "admin";
						password = "admin";
						role = "admin";
					} else if(j==1) {
						login = "administrative";
						password = "administrative";
						role = "administrative";
					} else if(j==2) {
						login = "physical";
						password = "physical";
						role = "physical";
					}
					
					Date registration = new Date();
					String name2 = faker.name().firstName();
					String surname = faker.name().lastName();
					String address = faker.address().fullAddress();
					
					//User user = new User(login, password, registration, name2, surname, role, address);
					//user.setClub(club);
					//userService.saveUser(user);
				}
			}
			
			// dodawanie pracownik�w
			for(int g=0; g<30; g++) {
				String nameWorker = faker.name().firstName();
				String surnameWorker = faker.name().lastName();
				float earnings = 2000 + generator.nextInt(4000);
				String[] roles = {"Greenkeepers", "Accountants", "Masseurs", "Cleaners", "Players"};
				String[] positions = {"Goalkeeper", "Defender", "Midfielder", "Striker"};
				String role = roles[generator.nextInt(roles.length)];
				
				if(g<23)
				{
					boolean isPlayer = true;
					boolean isInjured = false;
					int shirtNumber = generator.nextInt(23) + 1;
					String strongFoot = "Right";
					int height = 170 + generator.nextInt(30);
					int weight = 170 + generator.nextInt(30);
					String position = positions[generator.nextInt(positions.length)];
					Worker worker = new Worker(nameWorker, surnameWorker, earnings, role,  isPlayer,
							isInjured, shirtNumber, strongFoot, height, weight, position);
					worker.setClub(club);
					workerService.saveWorker(worker);
				} else {
					Worker worker = new Worker(nameWorker, surnameWorker, earnings, role);
					worker.setClub(club);
					workerService.saveWorker(worker);
				}
			}
			
			// dodawanie budynk�w klubowych
			
//			for(int h=0; h<5; h++) {
//				String nameBuilding = faker.company().name();
//				String addressBuilding = faker.address().fullAddress();
//				float surface = 200 + generator.nextInt(2000);
//				String[] types = {"Pitch", "Training", "Medical", "Research", "Warehouse"};
//				String type = types[generator.nextInt(types.length)];
//				if(h==0)
//					type = "Stadium";
//				Building building = new Building(surface, nameBuilding, addressBuilding, type);
//				building.setClub(club);
//				buildingService.saveBuilding(building);
//			}
		}
		return "redirect:/user/list";
	}
}