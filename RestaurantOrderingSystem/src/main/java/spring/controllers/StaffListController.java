package spring.controllers;

import spring.models.OrderHistory;
import spring.models.Permission;
import spring.models.StaffList;
import spring.models.User;

public class StaffListController {
	private StaffList staffList;

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
		
		//TODO save user to db
		
		Permission permission = new Permission(false, canViewOrders, canEditStaff, canEditMenu);
		addStaffMember(staffUser, permission);
		
		return staffUser;
	}
	
	private void  addStaffMember(User user, Permission permission) {
		staffList.addStaffMember(user, permission);
		//TODO save staffList to DB
	}
	
	public void removeStaffMember(User user) {
		staffList.removeStaffMember(user);
	}
}
