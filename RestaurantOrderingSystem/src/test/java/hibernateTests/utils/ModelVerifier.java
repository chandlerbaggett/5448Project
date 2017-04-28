package hibernateTests.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import spring.models.OrderHistory;
import spring.models.OrderItem;
import spring.models.Permission;
import spring.models.StaffList;
import spring.models.StaffMember;
import spring.models.User;

public class ModelVerifier {
	public static void verifyUsers(User user1, User user2) {
		   assertTrue(user1.getId() == user2.getId());
		   assertEquals(user1.getUserName(), user2.getUserName());
		   assertEquals(user1.getPassword(), user2.getPassword());
		   assertEquals(user1.getDisplayName(), user2.getDisplayName());
		   
		   verifyOrderHistories(user1.getOrderHistory(), user2.getOrderHistory());
	}
	
	public static void verifyOrderHistories(OrderHistory o1, OrderHistory o2) {
		//TODO enable when order histories are saved
//		assertEquals(o1.getAllOrders().size(), o2.getAllOrders().size());
//		for (int x=0; x < o1.getAllOrders().size(); x++) {
//			verifyOrderItems(o1.getAllOrders().toArray(new OrderItem[0])[x],
//							 o2.getAllOrders().toArray(new OrderItem[0])[x]);
//			}
	}
	
	public static void verifyOrderItems(OrderItem i1, OrderItem i2) {
		//TODO
	}

	public static void verifyStaffMembers(StaffMember member, StaffMember member2) {
		assertTrue(member.getId() == member2.getId());
		verifyUsers(member.getUser(), member2.getUser());
		verifyPermissions(member.getPermission(), member.getPermission());
	}

	public static void verifyPermissions(Permission permission, Permission permission2) {
		assertTrue(permission.canEditRestaurantMenu() == permission2.canEditRestaurantMenu());
		assertTrue(permission.canEditRestaurantStaff() == permission2.canEditRestaurantStaff());
		assertTrue(permission.canMakeOrders() == permission2.canMakeOrders());
		assertTrue(permission.canViewRestaurantOrders() == permission2.canViewRestaurantOrders());		
	}
	
	public static void verifyStaffLists(StaffList list1, StaffList list2) {
		assertTrue(list1.getId() == list2.getId());
		assertTrue(list1.getStaffMembers().size() == list2.getStaffMembers().size());  
		
		for (int x=0; x < list1.getStaffMembers().size(); x++) {
			verifyStaffMembers(list1.getStaffMembers().toArray(new StaffMember[0])[x],
							   list2.getStaffMembers().toArray(new StaffMember[0])[x]);
		}
	}
}
