package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import spring.formModels.CreateUser;
import spring.models.OrderHistory;
import spring.models.Permission;
import spring.models.StaffList;
import spring.models.StaffMember;
import spring.models.User;
import utils.DBManager;

@Controller
public class StaffListController {
	@GetMapping("/manageStaff")
	public String loadPage(Model model) {
		StaffList staffList = DBManager.getRestaurant().getStaff();
		
		model.addAttribute("lists", staffList.getStaffMembers());
		return "manageStaff";
	}
	
	@PostMapping("/manageStaff/remove")
	public ModelAndView removeUser(User user, Model model) {
		removeStaffMember(user);
		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageStaff/"));
	}
	
	@PostMapping("/manageStaff/add")
	public ModelAndView addUser(CreateUser user, Model model) {
		if (user.isAdmin()) {
			createAdminAccount(user.getUserName(), user.getPassword());
		
		} else {
			createStaffAccount(user.getUserName(), user.getPassword());
		}
		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageStaff/"));
	}
	
	public User createStaffAccount(String userName, String password) {
		boolean canViewOrders = true;
		boolean canManageRestaurant = false;
		
		return buildUser(userName, password, canViewOrders, canManageRestaurant);
	}

	public User createAdminAccount(String userName, String password) {
		boolean canViewOrders = true;
		boolean canManageRestaurant = true;
				
		return buildUser(userName, password, canViewOrders, canManageRestaurant);
	}
	
	private User buildUser(String userName, String password, boolean canViewOrders, boolean canManageRestaurant) {
		User staffUser = new User();
		staffUser.setUserName(userName);
		staffUser.setPassword(password);
		
		staffUser.setDisplayName(userName);
		staffUser.setOrderHistory(new OrderHistory());
		
		DBManager.saveModel(staffUser);
		
		Permission permission = new Permission(false, canViewOrders, canManageRestaurant);
		addStaffMember(staffUser, permission);
		
		return staffUser;
	}
	
	private void  addStaffMember(User user, Permission permission) {
		StaffList staffList = DBManager.getRestaurant().getStaff();
		
		staffList.addStaffMember(user, permission);
		DBManager.saveModel(staffList);
	}
	
	public void removeStaffMember(User user) {
		StaffList staffList = DBManager.getRestaurant().getStaff();
		
		for (StaffMember member : staffList.getStaffMembers()) {
			if (member.getUser().getId() == user.getId()) {
				user = member.getUser();
			}
		}
		
		staffList.removeStaffMember(user);
		DBManager.saveModel(staffList);
	}
}
