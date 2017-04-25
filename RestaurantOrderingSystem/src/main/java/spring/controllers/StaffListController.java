package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.models.OrderHistory;
import spring.models.Permission;
import spring.models.StaffList;
import spring.models.User;
import utils.DBManager;

@Controller
public class StaffListController {
	private StaffList staffList = new StaffList();

	@GetMapping("/manageStaff")
	public String welcome(Model model) {
		buildTest();
		model.addAttribute("lists", staffList.getStaffMembers());
		return "manageStaff";
	}
	
	private void buildTest() {
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
		staffList.removeStaffMember(user);
		DBManager.saveModel(staffList);
	}
}
