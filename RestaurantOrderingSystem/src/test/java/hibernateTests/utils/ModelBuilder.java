package hibernateTests.utils;

import spring.models.Menu;
import spring.models.MenuItem;
import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.OrderItem;
import spring.models.Permission;
import spring.models.RealRestaurant;
import spring.models.StaffList;
import spring.models.StaffMember;
import spring.models.User;
import utils.DBManager;

public class ModelBuilder {
	public static User buildUser(String userName, String password, OrderHistory orderHistory) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		user.setDisplayName(userName);
		user.setOrderHistory(orderHistory);
		
		return user;
	}
	
	public static RealRestaurant buildRestaurant() {
		RealRestaurant restaurant = new RealRestaurant();
		
		restaurant.setName("name");
		restaurant.setLocation("place");
		restaurant.setIsOpen(true);
		
		StaffList list = buildStaffList();
		
		OrderHistory staffHistory = buildOrderHistory();
		list.addStaffMember(buildUser("staff", "pass", staffHistory), new Permission(false, true, false));
		
		OrderHistory adminHistory = buildOrderHistory();
		list.addStaffMember(buildUser("admin", "pass", adminHistory), new Permission(false, true, true));
		
		restaurant.setStaff(list);
		
		restaurant.setMenu(buildMenu());
		
		restaurant.setOrders(buildOrderHistory());
		
		
		DBManager.saveModel(restaurant);
		
		return restaurant;
	}
	
	public static Menu buildMenu() {
		return new Menu();
	}
	
	public static OrderHistory buildOrderHistory() {
		OrderHistory history = new OrderHistory();
		return history;
	}
	
	public static Permission buildPermission() {
		return new Permission(false, true, false);
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
