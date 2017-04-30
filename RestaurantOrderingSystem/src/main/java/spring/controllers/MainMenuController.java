package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.models.Permission;
import utils.DBManager;
import utils.TestDataBuilder;

@Controller
public class MainMenuController {
	@GetMapping("/home")
	public String home(Model model) {
		Permission permission = null;
		
		if (DBManager.getRestaurant().getStaff() != null) {
			permission = DBManager.getRestaurant().getStaff().getPermissionForStaff(DBManager.getLoggedInUser());	
		}
		
		if (permission != null) {
			System.out.println("have permission");
			model.addAttribute("showPlacedOrders", permission.canViewRestaurantOrders());
			model.addAttribute("showPlaceOrder", permission.canMakeOrders());
			model.addAttribute("showEditMenu", permission.canManageRestaurant());
			model.addAttribute("showManageStaff", permission.canManageRestaurant());
			
		} else {
			System.out.println("no permission");
			model.addAttribute("showPlacedOrders", false);
			model.addAttribute("showPlaceOrder", true);
			model.addAttribute("showEditMenu", false);
			model.addAttribute("showManageStaff", false);
		}
		
		return "mainMenu";
	}
}