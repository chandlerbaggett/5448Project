package spring.controllers;

import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.User;

public class OrderHistoryController {
	
	private OrderHistory orderHistory;
	
	public void markOrderCompleted(Order order){
		order.setOrderStatus("Completed");
		orderHistory.addOrder(order);
	}
	
	public OrderHistory viewOrderHistory(User user){
		return user.getOrderHistory();		
	}
	
	public OrderHistory viewOrderHistory(){
		return orderHistory;
	}

}
