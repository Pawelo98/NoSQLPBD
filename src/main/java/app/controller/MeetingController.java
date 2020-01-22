package app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.Building;
import app.entity.Club;
import app.entity.Invite;
import app.entity.Meeting;
import app.entity.User;
import app.service.BuildingService;
import app.service.InvitesService;
import app.service.MeetingsService;
import app.service.UserService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class MeetingController {
	
	@Autowired
	MeetingsService meetingsService;
	
	@Autowired
	InvitesService invitesService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/meetingFormUpdate")
	public String showExistingMeetingForm(@RequestParam("meetingId") int id, Model model) {
		Meeting meeting = meetingsService.getMeeting(id);
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User curr = userService.getUser(username);
		List<Building> buildings = buildingService.getBuildings(curr.getClub());
		model.addAttribute("buildings", buildings);
		
		model.addAttribute("meeting", meeting);
		return "meeting-form";
	}
	
	@GetMapping("/meetingFormAdd")
	public String showNewMeetingForm(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User curr = userService.getUser(username);
		List<Building> buildings = buildingService.getBuildings(curr.getClub());
		model.addAttribute("buildings", buildings);
		
		Meeting meeting = new Meeting();
		meeting.setInitiator(curr.getUsername());
		model.addAttribute("meeting", meeting);
		return "meeting-form";
	}
	
	@PostMapping("/saveMeeting")
	public String saveMeeting(@ModelAttribute("meeting") Meeting meeting){
		
		meeting.setMeeting_id(90);
		
		meeting.setInvites(null);
	
		meetingsService.saveMeeting(meeting);
		
		return "redirect:/meetings";
	}
	
	@PostMapping("/saveInvite")
	public String saveInvite(@ModelAttribute("invite") Invite invite){
	
		invitesService.saveInvite(invite);
		
		return "redirect:/meetings";
	}
	
	@GetMapping("/sendInvite")
	public String showInviteForm(@RequestParam("meetingId") int id, Model model) {
		
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		
		Meeting meeting = meetingsService.getMeeting(id);
		model.addAttribute("meeting", meeting);
		
		Invite invite = new Invite();
		invite.setMeeting(meeting.getMeeting_id());
		model.addAttribute("invite", invite);
		return "invite-form";
	}
}
