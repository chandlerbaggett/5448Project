package spring.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORIES")
public class OrderHistory extends Model {
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Order> orders;
	
	public OrderHistory() {
		super();
		orders = new HashSet<Order>();
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public Set<Order> getOrdersByStatus(String status) {
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
