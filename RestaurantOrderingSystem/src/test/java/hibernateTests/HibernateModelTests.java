package hibernateTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import hibernateTests.utils.ModelBuilder;
import hibernateTests.utils.ModelVerifier;
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

public class hibernateModelTests {
   @Test
   public void testUserModel() {
	   OrderHistory history = ModelBuilder.buildOrderHistory();
	   
	   User s = ModelBuilder.buildUser("steve", "", history);
	   DBManager.saveModel(s);
	   User r = DBManager.getUser("steve");
		
	   ModelVerifier.verifyUsers(s, r);
   }
   
   @Test
   public void testRestaurantModel() {
	   RealRestaurant restaurant1 = ModelBuilder.buildRestaurant();
	   
	   DBManager.saveModel(restaurant1);
	   
	   RealRestaurant restaurant2 = (RealRestaurant) DBManager.getModel(RealRestaurant.class, restaurant1.getId());
	   
	   ModelVerifier.verifyRestaurants(restaurant1, restaurant2);
   }
   
   @Test
   public void testStaffMemberModel() {
	   OrderHistory history = ModelBuilder.buildOrderHistory();
	   User user = ModelBuilder.buildUser("greg", "", history);
	   Permission permission = ModelBuilder.buildPermission();
	   
	   StaffMember member = ModelBuilder.buildStaffMember(user, permission);
	   DBManager.saveModel(member);
	   
	   StaffMember member2 = (StaffMember) DBManager.getModel(StaffMember.class, member.getId());
	   
	   ModelVerifier.verifyStaffMembers(member, member2);
   }
   
   @Test
   public void testStaffListModel() {
	   StaffList list1 = ModelBuilder.buildStaffList();
	   
	   for (int x=0; x < 3; x++) {
		   OrderHistory history = ModelBuilder.buildOrderHistory();
		   User user = ModelBuilder.buildUser("greg"+x, "", history);
		   Permission permission = ModelBuilder.buildPermission();
		   
		   list1.addStaffMember(user, permission);
	   }
	   
	   DBManager.saveModel(list1);
	   
	   StaffList list2 = (StaffList) DBManager.getModel(StaffList.class, list1.getId());
	   
	   ModelVerifier.verifyStaffLists(list1, list2);
   }
   
   @Test
   public void testMenuItemModel() {
	   MenuItem item1 = ModelBuilder.buildMenuItem("burger", 5);
	   
	   DBManager.saveModel(item1);
	   
	   MenuItem item2 = (MenuItem) DBManager.getModel(MenuItem.class, item1.getId());
	   
	   ModelVerifier.verifyMenuItems(item1, item2);
   }
   
   @Test
   public void testOrderItemModel() {
	   MenuItem menuItem = ModelBuilder.buildMenuItem("pizza", 10);
	   DBManager.saveModel(menuItem);
	   
	   OrderItem item1 = ModelBuilder.buildOrderItem(menuItem, 6);
	   
	   DBManager.saveModel(item1);
	   
	   OrderItem item2 = (OrderItem) DBManager.getModel(OrderItem.class, item1.getId());
	   
	   ModelVerifier.verifyOrderItems(item1, item2);   
   }
   
   @Test
   public void testOrderModel() {
	   Order order1 = new Order();
	   Date date = new Date();
	   date.setTime(System.currentTimeMillis());
	   order1.setOrderDate(5l);
	   order1.setorderId(5);
	   order1.setOrderStatus("stuff");

	   List<OrderItem> items = new ArrayList<OrderItem>();
	   
	   MenuItem menuItem1 = ModelBuilder.buildMenuItem("pizza", 10);
	   DBManager.saveModel(menuItem1);
	   items.add(ModelBuilder.buildOrderItem(menuItem1, 6));
	   
	   MenuItem menuItem2 = ModelBuilder.buildMenuItem("pizza", 10);
	   DBManager.saveModel(menuItem2);
	   items.add(ModelBuilder.buildOrderItem(menuItem2, 2));
	   
	   for (OrderItem item : items) {
		   DBManager.saveModel(item);
	   }

	   order1.setOrderItems(items);

	   DBManager.saveModel(order1);
	   
	   Order order2 = (Order) DBManager.getModel(Order.class, order1.getId());
	   
	   ModelVerifier.verifyOrders(order1, order2);
   }
   
   @Test
   public void testOrderHistoryModel() {
	   OrderHistory history1 = new OrderHistory();
	   
	   Order subOrder1 = new Order();
	   subOrder1.setOrderDate(5l);
	   subOrder1.setorderId(5);
	   subOrder1.setOrderStatus("stuff");

	   List<OrderItem> items1 = new ArrayList<OrderItem>();
	   
	   MenuItem menuItem1 = ModelBuilder.buildMenuItem("pizza", 10);
	   DBManager.saveModel(menuItem1);
	   items1.add(ModelBuilder.buildOrderItem(menuItem1, 6));
	   
	   MenuItem menuItem2 = ModelBuilder.buildMenuItem("pizza", 10);
	   DBManager.saveModel(menuItem2);
	   items1.add(ModelBuilder.buildOrderItem(menuItem2, 2));
	   
	   for (OrderItem item : items1) {
		   DBManager.saveModel(item);
	   }
	   subOrder1.setOrderItems(items1);
	   DBManager.saveModel(subOrder1);
	   history1.addOrder(subOrder1);
	   
	   Order subOrder2 = new Order();
	   subOrder2.setOrderDate(5l);
	   subOrder2.setorderId(5);
	   subOrder2.setOrderStatus("stuff");

	   List<OrderItem> items2 = new ArrayList<OrderItem>();
	   items2.add(ModelBuilder.buildOrderItem(ModelBuilder.buildMenuItem("pizza", 10), 6));
	   items2.add(ModelBuilder.buildOrderItem(ModelBuilder.buildMenuItem("burger", 5), 2));
	   items2.add(ModelBuilder.buildOrderItem(ModelBuilder.buildMenuItem("shake", 1), 3));
	   
	   for (OrderItem item : items2) {
		   DBManager.saveModel(item);
	   }
	   DBManager.saveModel(subOrder2);
	   history1.addOrder(subOrder2);
	   
	   DBManager.saveModel(history1);
	   
	   OrderHistory history2 = (OrderHistory) DBManager.getModel(OrderHistory.class, history1.getId());
	   
	   ModelVerifier.verifyOrderHistories(history1, history2);
   }
}
