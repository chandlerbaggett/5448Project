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
	//TODO access this from the restaurant
	private StaffList staffList = new StaffList();

	@GetMapping("/manageStaff")
	public String loadPage(Model model) {
		if (DBManager.getModel(User.class, 1) == null) {
			buildTestData();
		}
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
	
	private void buildTestData() {
		createStaffAccount("steve", "");
		createStaffAccount("greg", "");
		createStaffAccount("sven", "");
		
		createAdminAccount("tom", "");
	}
	
	public User createStaffAccount(String userName, String password) {
		boolean canViewOrders = true;
		boolean canEditStaff = false;
		boolean canEditMenu = false;
		
		return buildUser(userName, password, canViewOrders, canEditStaff, canEditMenu);
	}

	public User createAdminAccount(String userName, String password) {
		boolean canViewOrders = true;
		boolean canEditStaff = true;
		boolean canEditMenu = true;
				
		return buildUser(userName, password, canViewOrders, canEditStaff, canEditMenu);
	}
	
	private User buildUser(String userName, String password, boolean canViewOrders, boolean canEditStaff,
			boolean canEditMenu) {
		User staffUser = new User();
		staffUser.setUserName(userName);
		staffUser.setPassword(password);
		
		staffUser.setDisplayName(userName);
		staffUser.setOrderHistory(new OrderHistory());
		
		DBManager.saveModel(staffUser);
		
		Permission permission = new Permission(false, canViewOrders, canEditStaff, canEditMenu);
		addStaffMember(staffUser, permission);
		
		return staffUser;
	}
	
	private void  addStaffMember(User user, Permission permission) {
		staffList.addStaffMember(user, permission);
		DBManager.saveModel(staffList);
	}
	
	public void removeStaffMember(User user) {
		for (StaffMember member : staffList.getStaffMembers()) {
			if (member.getUser().getId() == user.getId()) {
				user = member.getUser();
			}
		}
		
		staffList.removeStaffMember(user);
		DBManager.saveModel(staffList);
	}
}
