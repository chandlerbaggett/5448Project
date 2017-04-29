package utils;

import spring.models.Menu;
import spring.models.OrderHistory;
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
		
		//TODO finish when order history annotated
//		DBManager.saveModel(history);
		
		return history;
	}

	private static StaffList buildBaseStaffList() {
		StaffList staff = new StaffList();
		
		User staffUser = buildStaffUser();
		Permission staffPermission = new Permission(false, true, false, false);
		staff.addStaffMember(staffUser, staffPermission);
		
		User adminUser = buildAdminUser();
		Permission adminPermission = new Permission(false, true, true, true);
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