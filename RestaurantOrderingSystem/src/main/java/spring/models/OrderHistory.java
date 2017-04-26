package spring.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;

public class OrderHistory {
	@ElementCollection
	private Collection<Order> orders = new ArrayList<Order>();
	
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
