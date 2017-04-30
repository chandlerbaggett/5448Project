package spring.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORIES")
public class OrderHistory extends Model {
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Collection<Order> orders;
	
	public OrderHistory() {
		super();
		orders = new ArrayList<Order>();
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
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
