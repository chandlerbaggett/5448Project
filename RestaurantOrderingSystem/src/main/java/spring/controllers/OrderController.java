package spring.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.models.MenuItem;
import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.OrderItem;
import spring.models.OrderMemento;
import spring.models.RealRestaurant;
import spring.models.Restaurant;
import spring.models.User;
import utils.DBManager;

@Controller
public class OrderController {
	
	private Order order;	
	private User customer;
	private Restaurant restaurant = new RealRestaurant();
	private ArrayList<OrderMemento> mementos = new ArrayList<OrderMemento>();
	
	@GetMapping("/orderWelcome")
	public String welcome(Model model) {
		Order myOrder = createOrder();
		//myOrder.setorderId(105);
		addToOrder(new MenuItem("Hamburger", "Juicy Hamburger on a Sesame Seed Bun", 9.99,null),myOrder);
		//myOrder.addItem(new OrderItem(new MenuItem("Hamburger", "Juicy Hamburger on a Sesame Seed Bun", 9.99,null)));
		model.addAttribute("order", myOrder);
		model.addAttribute("items", myOrder.getOrderItems() );
		//model.addAttribute("controller", this );
		return "order";
	}
	
	public Order createOrder(){
		this.order = new Order();		
		//OrderHistory orderHistory = customer.getOrderHistory();
		//orderHistory.addOrder(order);	
		return order;
	}
	
	public void submitOrder(Order order){
		if (restaurant.isOpen()){
			order.setOrderStatus("Submitted");
			DBManager.saveModel(order);
			OrderHistory orderHistory = restaurant.getOrders();
			orderHistory.addOrder(order);
		}
		else{
			System.out.println("Restaurant is currently closed. Order not submitted.");
		}
	}
	
	public void cancelOrder(Order order){
		order.setOrderStatus("Cancelled");
		DBManager.saveModel(order);
	}
	
	public void saveOrder(Order order){		
		mementos.add(order.createMemento());
		DBManager.saveModel(order);
		System.out.println("Saving order");
	}
	
	public Order resumeOrder(){		
		order.setMemento(mementos.get(1));
		DBManager.getModel(Order.class, order.getId());
		return order;
	}
	
	public void addToOrder(MenuItem menuItem, Order order){
		OrderItem orderItem = new OrderItem(menuItem);
		order.addItem(orderItem);
		DBManager.saveModel(order);
	}
	
	public void removeFromOrder(OrderItem orderItem, Order order){
		order.removeItem(orderItem);
		DBManager.saveModel(order);
	}
	
	public double displayTotal(Order order){
		return order.calculateOrderTotal();
	}
	
	public Order duplicateOrder(){
		Order duplicate = order.clone();
		order.setOrderStatus("Active");
		DBManager.saveModel(order);
		return duplicate;
	}
	

}
