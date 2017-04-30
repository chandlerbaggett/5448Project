package utils;

import java.util.ArrayList;
import java.util.List;

import hibernateTests.utils.ModelBuilder;
import spring.models.Menu;
import spring.models.MenuItem;
import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.OrderItem;
import spring.models.Permission;
import spring.models.RealRestaurant;
import spring.models.StaffList;
import spring.models.User;

public class TestDataBuilder {
	public static void buildTestData() {
		buildBaseRestaurant();
		buildBaseUser();
	}
	
	public static User buildBaseUser() {
		User user = new User();
		user.setUserName("username");
		user.setPassword("password");
		
		user.setDisplayName("username");
		user.setOrderHistory(buildBaseOrderHstory());
				
		DBManager.saveModel(user);
		
		return user;
	}
	
	private static RealRestaurant buildBaseRestaurant() {
		RealRestaurant restaurant = new RealRestaurant();
		
		restaurant.setStaff(buildBaseStaffList());
		restaurant.setMenu(buildBaseMenu());
		restaurant.setOrders(buildBaseOrderHstory());
		
		restaurant.setName("name");
		restaurant.setLocation("place");
		
		restaurant.setIsOpen(true);
		
		DBManager.saveModel(restaurant);
		
		return restaurant;
	}

	private static OrderHistory buildBaseOrderHstory() {
		OrderHistory history = new OrderHistory();
		history.addOrder(buildOrder());
		history.addOrder(buildOrder());
		
		return history;
	}
	
	public static Order buildOrder() {
		Order subOrder1 = new Order();
		subOrder1.setOrderDate(5l);
		subOrder1.setorderId(500);
		subOrder1.setOrderStatus("COMPLETED");

		List<OrderItem> items1 = new ArrayList<OrderItem>();
		items1.add(buildOrderItem(buildMenuItem("pizza", 10), 6));
		items1.add(buildOrderItem(buildMenuItem("burger", 4), 2));
		subOrder1.setOrderItems(items1);
		
		return subOrder1;
	}

	public static OrderItem buildOrderItem(MenuItem menuItem, int quantity) {
		OrderItem item = new OrderItem();
		item.setQuantity(quantity);
		item.setMenuItem(menuItem);
		
		return item;
	}
	
	public static MenuItem buildMenuItem(String name, double price) {
		MenuItem item = new MenuItem();
		item.setName(name);
		item.setPrice(price);
		item.setDescroption(name+price);
		
		return item;
	}

	private static StaffList buildBaseStaffList() {
		StaffList staff = new StaffList();
		
		User staffUser = buildStaffUser();
		Permission staffPermission = new Permission(false, true, false);
		staff.addStaffMember(staffUser, staffPermission);
		
		User adminUser = buildAdminUser();
		Permission adminPermission = new Permission(false, true, true);
		staff.addStaffMember(adminUser, adminPermission);
		
		DBManager.saveModel(staff);
		
		return staff;
	}

	private static Menu buildBaseMenu() {
		Menu menu = new Menu();
		//TODO finish when order history annotated
//		DBManager.saveModel(menu);
		
		return menu;
	}
	
	private static User buildStaffUser() {
		User staffUser = new User();
		staffUser.setUserName("staff");
		staffUser.setPassword("password");
		
		staffUser.setDisplayName("staff");
		staffUser.setOrderHistory(buildBaseOrderHstory());
		
		DBManager.saveModel(staffUser);
		
		return staffUser;
	}
	
	private static User buildAdminUser() {
		User staffUser = new User();
		staffUser.setUserName("admin");
		staffUser.setPassword("password");
		
		staffUser.setDisplayName("admin");
		staffUser.setOrderHistory(buildBaseOrderHstory());
		
		DBManager.saveModel(staffUser);
		
		return staffUser;
	}
}