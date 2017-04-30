package hibernateTests.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

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
		   verifyOrderHistories(user1.getOrderHistory(), user2.getOrderHistory());
	}
	
	public static void verifyOrderHistories(OrderHistory o1, OrderHistory o2) {
		assertTrue("ids should be equal", o1.getId() == o2.getId());
		assertEquals("num orders should be the same", o1.getOrders().size(), o2.getOrders().size());
		
		Order[] orders1 = o1.getOrders().toArray(new Order[0]);
		Order[] orders2 = o2.getOrders().toArray(new Order[0]);
		Comparator<Order> sort = new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				return o1.getId()-o2.getId();
			}	
		};
		
		Arrays.sort(orders1, sort);
		Arrays.sort(orders2, sort);
		
		for (int x=0; x < orders1.length; x++) {
			verifyOrders(orders1[x], orders2[x]);
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
		assertTrue(order1.getOrderDate() == order2.getOrderDate());
		
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
	}

	public static void verifyStaffMembers(StaffMember member, StaffMember member2) {
		assertTrue("ids should be equal", member.getId() == member2.getId());
		verifyUsers(member.getUser(), member2.getUser());
		verifyPermissions(member.getPermission(), member.getPermission());
	}

	public static void verifyPermissions(Permission permission, Permission permission2) {
		assertTrue("permissions should match", permission.canManageRestaurant() == permission2.canManageRestaurant());
		assertTrue("permissions should match", permission.canMakeOrders() == permission2.canMakeOrders());
		assertTrue("permissions should match", permission.canViewRestaurantOrders() == permission2.canViewRestaurantOrders());		
	}
	
	public static void verifyStaffLists(StaffList list1, StaffList list2) {
		assertTrue("ids should be equal", list1.getId() == list2.getId());
		assertEquals("num staff should be the same", list1.getStaffMembers().size(), list2.getStaffMembers().size());  
		
		
		StaffMember[] staff1 = list1.getStaffMembers().toArray(new StaffMember[0]);
		StaffMember[] staff2 = list2.getStaffMembers().toArray(new StaffMember[0]);
		
		Comparator<StaffMember> sort = new Comparator<StaffMember>() {
			
			@Override
			public int compare(StaffMember o1, StaffMember o2) {
				return o1.getId()-o2.getId();
			}
		};
		
		Arrays.sort(staff1, sort);
		Arrays.sort(staff2, sort);
		
		for (int x=0; x < staff1.length; x++) {
			verifyStaffMembers(staff1[x], staff2[x]);
		}
	}
	
	public static void verifyRestaurants(RealRestaurant restaurant1, RealRestaurant restaurant2) {
		assertTrue("ids should be equal", restaurant1.getId() == restaurant2.getId());
		assertEquals("locations should be the same", restaurant1.getLocation(), restaurant2.getLocation());
		assertEquals("names should be equal", restaurant1.getName(), restaurant2.getName());
		assertTrue("open status should be the same", restaurant1.isOpen() == restaurant2.isOpen());
		
		//TODO add when fully implemented
//		verifyMenus(restaurant1.getMenu(), restaurant2.getMenu());
		verifyOrderHistories(restaurant1.getOrders(), restaurant2.getOrders());
		verifyStaffLists(restaurant1.getStaff(), restaurant2.getStaff());
	}
	
	public static void verifyMenus(Menu menu1, Menu menu2) {
		//TODO when menu fully implemented
	}
}
