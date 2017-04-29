package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import spring.formModels.CreateUser;
import spring.models.OrderHistory;
import spring.models.User;
import utils.DBManager;
import utils.TestDataBuilder;

@Controller
public class UserController {
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/createAccount")
	public String loadCreateAccount(Model model) {
		return "createAccount";
	}
	
	@PostMapping("/createAccount")
	public ModelAndView createAccount(CreateUser user, Model model) {
		//TODO login to created account
		User staffUser = new User();
		staffUser.setUserName(user.getUserName());
		staffUser.setPassword(user.getPassword());
		
		staffUser.setDisplayName(user.getUserName());
		staffUser.setOrderHistory(new OrderHistory());
		
		DBManager.saveModel(staffUser);
		
		//TODO add confirmation message to model
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/home/"));
	}
	
	@PostMapping("/logout")
	public ModelAndView logOutAction(Model model) {
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/home/"));
	}
}
