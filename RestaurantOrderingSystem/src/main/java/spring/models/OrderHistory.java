package spring.models;

import java.util.Collection;

public class OrderHistory {
	private Collection<Order> orders;
	
	public Collection<Order> getAllOrders() {
		return orders;
	}
	
	public Collection<Order> getOrdersByStatus(String status) {
		//TODO filter based on status
		return orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
	}
}
