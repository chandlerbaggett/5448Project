package spring.models;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

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
		Set<Order> tempSet = new HashSet<Order>();
		tempSet.addAll(orders);
		tempSet.removeIf(new Predicate<Order>() {
			@Override
			public boolean test(Order order) {
				return !order.getOrderStatus().equals(status);
			}
		});
		return tempSet;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
	}
}
