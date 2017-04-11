package spring.controllers;

import spring.models.Order;

public class OrderController {
	
	Order order;
	
	public Order createOrder(){
		this.order = new Order();
		return order;
	}
	
	public void submitOrder(Order order){
		//ADD CODE
	}
	
	public void cancelOrder(Order order){
		//ADD CODE
	}
	
	public void saveOrder(Order order){
		//ADD CODE
	}
	
	public Order resumeOrder(Order order){
		//ADD CODE
	}
	
	public void addToOrder(OrderItem orderitem, Order order){
		//ADD CODE
	}
	
	public void removeFromOrder(OrderItem orderitem, Order order){
		//ADD CODE
	}
	
	public double displayTotal(Order order){
		//ADD CODE
	}
	
	public Order duplicateOrder(){
		//ADD CODE
	}

}
