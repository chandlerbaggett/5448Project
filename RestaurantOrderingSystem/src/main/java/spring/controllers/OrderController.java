package spring.controllers;

import spring.models.MenuItem;
import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.OrderItem;
import spring.models.OrderMemento;
import spring.models.Restaurant;
import spring.models.User;

public class OrderController {
	
	private Order order;	
	private User customer;
	private Restaurant restaurant;
	
	public Order createOrder(){
		this.order = new Order();		
		OrderHistory orderHistory = customer.getOrderHistory();
		orderHistory.addOrder(order);		
		return order;
	}
	
	public void submitOrder(Order order){
		if (restaurant.isOpen()){
			order.setOrderStatus("Submitted");
			OrderHistory orderHistory = restaurant.getOrders();
			orderHistory.addOrder(order);
		}
		else{
			System.out.println("Restaurant is currently closed. Order not submitted.");
		}
	}
	
	public void cancelOrder(Order order){
		order.setOrderStatus("Cancelled");
	}
	
	public void saveOrder(Order order){		
		order.createMemento();
	}
	
	public Order resumeOrder(){
		OrderMemento memento = new OrderMemento();
		order.setMemento(memento);
		return order;
	}
	
	public void addToOrder(MenuItem menuItem, Order order){
		OrderItem orderItem = new OrderItem(menuItem);
		order.addItem(orderItem);
	}
	
	public void removeFromOrder(OrderItem orderItem, Order order){
		order.removeItem(orderItem);
	}
	
	public double displayTotal(Order order){
		return order.calculateOrderTotal();
	}
	
	public Order duplicateOrder(){
		Order duplicate = order.clone();
		//order.setorderId();
		order.setOrderStatus("Active");
		return duplicate;
	}

}
