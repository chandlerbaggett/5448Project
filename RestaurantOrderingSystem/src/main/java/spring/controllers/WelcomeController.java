package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.models.Menu;
import spring.models.OrderHistory;
import spring.models.Permission;
import spring.models.RealRestaurant;
import spring.models.Restaurant;
import spring.models.StaffList;
import spring.models.User;
import utils.DBManager;

@Controller
public class WelcomeController {
	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("firstName", "Elizabeth");
		
		buildRestaurant();
		
		
		return "welcomeMessage";
	}
	
	//TODO extract into fake model builder class
	private void buildRestaurant() {
		RealRestaurant restaurant = new RealRestaurant();
		
		User staffUser = new User();
		staffUser.setUserName("steve");
		staffUser.setPassword("pass");
		
		staffUser.setDisplayName("steve");
		staffUser.setOrderHistory(new OrderHistory());
		
		DBManager.saveModel(staffUser);
		
		Permission permission = new Permission(false, true, false, false);
		
		StaffList staff = new StaffList();
		staff.addStaffMember(staffUser, permission);
	
		
		restaurant.setStaff(staff);
		restaurant.setMenu(new Menu());
		restaurant.setName("name");
		restaurant.setIsOpen(true);
		restaurant.setLocation("palce");
		restaurant.setOrders(new OrderHistory());
		
		DBManager.saveModel(restaurant);
	}
}