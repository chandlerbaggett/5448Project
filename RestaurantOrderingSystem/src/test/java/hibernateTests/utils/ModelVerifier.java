package hibernateTests.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class ModelVerifier {
	public static void verifyUsers(User user1, User user2) {
		   assertTrue("ids should be equal", user1.getId() == user2.getId());
		   assertEquals("user names should match", user1.getUserName(), user2.getUserName());
		   assertEquals("passwords should match", user1.getPassword(), user2.getPassword());
		   assertEquals("display names should match", user1.getDisplayName(), user2.getDisplayName());
		   
			//TODO enable when order histories are saved
//		   verifyOrderHistories(user1.getOrderHistory(), user2.getOrderHistory());
	}
	
	public static void verifyOrderHistories(OrderHistory o1, OrderHistory o2) {
		assertEquals("num orders should be the same", o1.getAllOrders().size(), o2.getAllOrders().size());
		
		for (int x=0; x < o1.getAllOrders().size(); x++) {
			verifyOrders(o1.getAllOrders().toArray(new Order[0])[x],
						 o2.getAllOrders().toArray(new Order[0])[x]);
		}
	}
	
	public static void verifyOrderItems(OrderItem item1, OrderItem item2) {
		assertTrue("ids should be equal", item1.getId() == item2.getId());
		assertTrue("item quantity should be the same", item1.getQuantity() == item2.getQuantity());
		verifyMenuItems(item1.getMenuItem(), item2.getMenuItem());
	}
	
	public static void verifyOrders(Order order1, Order order2) {
		assertTrue("ids should be equal", order1.getId() == order2.getId());
		assertEquals("order status should be the same", order1.getOrderStatus(), order2.getOrderStatus());
		assertEquals("order date should be the same", order1.getOrderDate(), order2.getOrderDate());
		
		assertTrue("num order items should be the same", order1.getOrderItems().size() == order2.getOrderItems().size());
		
		for (int x=0; x < order1.getOrderItems().size(); x++) {
			verifyOrderItems(order1.getOrderItems().toArray(new OrderItem[0])[x],
							 order2.getOrderItems().toArray(new OrderItem[0])[x]);
		}
	}
	
	public static void verifyMenuItems(MenuItem item1, MenuItem item2) {
		assertTrue("ids should be equal", item1.getId() == item2.getId());
		assertEquals("names should be equal", item1.getName(), item2.getName());
		assertEquals("descriptions should be equal", item1.getDescroption(), item2.getDescroption());
		assertTrue("prices should be equal", item1.getPrice() == item2.getPrice());
		assertEquals("images should be the same", item1.getImage(), item2.getImage());
	}

	public static void verifyStaffMembers(StaffMember member, StaffMember member2) {
		assertTrue("ids should be equal", member.getId() == member2.getId());
		verifyUsers(member.getUser(), member2.getUser());
		verifyPermissions(member.getPermission(), member.getPermission());
	}

	public static void verifyPermissions(Permission permission, Permission permission2) {
		assertTrue("permissions should match", permission.canEditRestaurantMenu() == permission2.canEditRestaurantMenu());
		assertTrue("permissions should match", permission.canEditRestaurantStaff() == permission2.canEditRestaurantStaff());
		assertTrue("permissions should match", permission.canMakeOrders() == permission2.canMakeOrders());
		assertTrue("permissions should match", permission.canViewRestaurantOrders() == permission2.canViewRestaurantOrders());		
	}
	
	public static void verifyStaffLists(StaffList list1, StaffList list2) {
		assertTrue("ids should be equal", list1.getId() == list2.getId());
		assertTrue("num staff should be the same", list1.getStaffMembers().size() == list2.getStaffMembers().size());  
		
		for (int x=0; x < list1.getStaffMembers().size(); x++) {
			verifyStaffMembers(list1.getStaffMembers().toArray(new StaffMember[0])[x],
							   list2.getStaffMembers().toArray(new StaffMember[0])[x]);
		}
	}
	
	public static void verifyRestaurants(RealRestaurant restaurant1, RealRestaurant restaurant2) {
		assertTrue("ids should be equal", restaurant1.getId() == restaurant2.getId());
		assertEquals("locations should be the same", restaurant1.getLocation(), restaurant2.getLocation());
		assertEquals("names should be equal", restaurant1.getName(), restaurant2.getName());
		assertTrue("open status should be the same", restaurant1.isOpen() == restaurant2.isOpen());
		
		//TODO add when fully implemented
//		verifyMenus(restaurant1.getMenu(), restaurant2.getMenu());
//		verifyOrderHistories(restaurant1.getOrders(), restaurant2.getOrders());
		verifyStaffLists(restaurant1.getStaff(), restaurant2.getStaff());
	}
	
	public static void verifyMenus(Menu menu1, Menu menu2) {
		//TODO when menu fully implemented
	}
}
