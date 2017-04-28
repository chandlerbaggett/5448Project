package hibernateTests.utils;

import spring.models.MenuItem;
import spring.models.OrderHistory;
import spring.models.OrderItem;
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

	public static MenuItem buildMenuItem(String name, double price) {
		MenuItem item = new MenuItem();
		item.setName(name);
		item.setPrice(price);
		item.setDescroption(name+price);
		//TODO what are we doing for images?
//		item.setImage(new Image());
		
		return item;
	}

	public static OrderItem buildOrderItem(MenuItem menuItem, int quantity) {
		OrderItem item = new OrderItem();
		item.setQuantity(quantity);
		item.setMenuItem(menuItem);
		
		return item;
	}
}
