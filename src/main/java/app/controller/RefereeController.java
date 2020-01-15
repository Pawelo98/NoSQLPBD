package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dao.RefereeDAO;
import app.entity.Referee;
import app.service.RefereeService;

//@Controller
//@RequestMapping("/referees")
public class RefereeController {

	@Autowired
	private RefereeService refereeService;
	
//	@GetMapping("")
//	public String listReferees(Model theModel) {
//		
//		List<Referee> theReferees = refereeService.getReferees();
//		
//		
//		theModel.addAttribute("referees", theReferees);
//		
//		return "referees-view";
//	}
}
