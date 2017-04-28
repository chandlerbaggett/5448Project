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
		   assertTrue("ids should be equal", user1.getId() == user2.getId());
		   assertEquals("user names should match", user1.getUserName(), user2.getUserName());
		   assertEquals("passwords should match", user1.getPassword(), user2.getPassword());
		   assertEquals("display names should match", user1.getDisplayName(), user2.getDisplayName());
		   
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
		assertTrue("sizes should be equal", list1.getStaffMembers().size() == list2.getStaffMembers().size());  
		
		for (int x=0; x < list1.getStaffMembers().size(); x++) {
			verifyStaffMembers(list1.getStaffMembers().toArray(new StaffMember[0])[x],
							   list2.getStaffMembers().toArray(new StaffMember[0])[x]);
		}
	}
}
