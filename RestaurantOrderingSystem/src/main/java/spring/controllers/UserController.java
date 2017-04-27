package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import spring.config.AuthenticationService;
import spring.formModels.CreateUser;
import spring.models.OrderHistory;
import spring.models.User;
import utils.DBManager;

@Controller
public class UserController {
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/createAccount")
	public String loadCreateAccount(Model model) {
		//TODO make user and save to db
		//TODO login to created account
		return "createAccount";
	}
	
	@PostMapping("/createAccount")
	public ModelAndView createAccount(CreateUser user, Model model) {
		//TODO make user and save to db
		//TODO login to created account
		
		
		User staffUser = new User();
		staffUser.setUserName(user.getUserName());
		staffUser.setPassword(user.getPassword());
		
		staffUser.setDisplayName(user.getUserName());
		staffUser.setOrderHistory(new OrderHistory());
		
		DBManager.saveModel(staffUser);
		
		//add confirmation message to model
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/welcome/"));
	}
	
	@PostMapping("/logout")
	public String logOutAction(Model model) {
		return "welcome";
	}
}
