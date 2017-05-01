package hibernateTests.utils;

import java.util.ArrayList;
import java.util.List;

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
	public static User buildUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		user.setDisplayName(userName);
		user.setOrderHistory(buildOrderHistory());
		
		return user;
	}
	
	public static RealRestaurant buildRestaurant() {
		RealRestaurant restaurant = new RealRestaurant();
		
		restaurant.setName("name");
		restaurant.setLocation("place");
		restaurant.setIsOpen(true);
		
		restaurant.setStaff(buildStaffList());
		
		restaurant.setMenu(buildMenu());
		
		restaurant.setOrders(buildOrderHistory());
		
		DBManager.saveModel(restaurant);
		
		return restaurant;
	}

	public static Menu buildMenu() {
		Menu menu = new Menu();
		menu.addMenuItem(buildMenuItem("burger", 1));
		menu.addMenuItem(buildMenuItem("pizza", 2));
		return menu;
	}
	
	public static OrderHistory buildOrderHistory() {
		OrderHistory history = new OrderHistory();
		
		history.addOrder(buildOrder());
		history.addOrder(buildOrder());
		
		return history;
	}

	public static Order buildOrder() {
		Order subOrder1 = new Order();
		subOrder1.setOrderDate(5l);
		subOrder1.setorderId(5);
		subOrder1.setOrderStatus("stuff");

		List<OrderItem> items1 = new ArrayList<OrderItem>();
		items1.add(ModelBuilder.buildOrderItem(ModelBuilder.buildMenuItem("pizza", 10), 6));
		items1.add(ModelBuilder.buildOrderItem(ModelBuilder.buildMenuItem("pizza", 10), 2));
		subOrder1.setOrderItems(items1);
		
		return subOrder1;
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

		list.addStaffMember(buildUser("staff", "pass"), new Permission(false, true, false));
		
		list.addStaffMember(buildUser("admin", "pass"), new Permission(false, true, true));
		
		return list;
	}

	public static MenuItem buildMenuItem(String name, double price) {
		MenuItem item = new MenuItem();
		item.setName(name);
		item.setPrice(price);
		item.setDescroption(name+price);
		
		DBManager.saveModel(item);
		
		return item;
	}

	public static OrderItem buildOrderItem(MenuItem menuItem, int quantity) {
		OrderItem item = new OrderItem();
		item.setQuantity(quantity);
		item.setMenuItem(menuItem);
		
		return item;
	}
	
}
