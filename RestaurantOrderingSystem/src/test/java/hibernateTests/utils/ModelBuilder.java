package hibernateTests.utils;

import spring.models.OrderHistory;
import spring.models.Permission;
import spring.models.StaffList;
import spring.models.StaffMember;
import spring.models.User;

public class ModelBuilder {
	public static User buildUser(String userName, String password, OrderHistory orderHistory) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		user.setDisplayName(userName);
		user.setOrderHistory(orderHistory);
		
		return user;
	}
	
	public static OrderHistory buildOrderHistory() {
		OrderHistory history = new OrderHistory();
		return history;
	}
	
	
	public static Permission buildPermission() {
		return new Permission(false, true, false, false);
	}
	
	public static StaffMember buildStaffMember(User user, Permission permission) {
		StaffMember member = new StaffMember();
		member.setUser(user);
		member.setPermission(permission);
	
		return member;
		
	}
	
	public static StaffList buildStaffList() {
		StaffList list = new StaffList();
		return list;
	}
}
