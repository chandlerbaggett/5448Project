package spring.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import spring.formModels.CreateOrderItem;
import spring.models.MenuItem;
import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.OrderItem;
import spring.models.OrderMemento;
import spring.models.Restaurant;
import spring.models.RestaurantProxy;
import spring.models.User;
import utils.DBManager;

@Controller
public class OrderController {
	
	private Order order;	
	private User customer;
	private Restaurant restaurant = DBManager.getRestaurant();
	private ArrayList<OrderMemento> mementos = new ArrayList<OrderMemento>();
	
	@GetMapping("/manageOrder")
	public String loadPage(Model model) {
		if (DBManager.getOrder() == null){
			buildTestData();
		}
		/*if (DBManager.getModel(Order.class, 1) == null) {
			buildTestData();
		}	*/			
		model.addAttribute("order", order);
		model.addAttribute("items", order.getOrderItems() );
		//model.addAttribute("controller", this );
		return "manageOrder";
	}
	
	private void buildTestData(){
		order = createOrder();
		//myOrder.setorderId(105);
		addToOrder(new MenuItem("Free Chips and Salsa", "Free Chips and Salsa Appetizer", 0.00,null));
		//myOrder.addItem(new OrderItem(new MenuItem("Hamburger", "Juicy Hamburger on a Sesame Seed Bun", 9.99,null)));
		restaurant.setIsOpen(true);
	}
	
	public Order createOrder(){
		this.order = new Order();		
		//OrderHistory orderHistory = customer.getOrderHistory();
		//orderHistory.addOrder(order);	
		return order;
	}
	
	@PostMapping("/manageOrder/submit")
	public ModelAndView submit(){
		submitOrder();		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}
	
	public void submitOrder(){
		restaurant.setIsOpen(true);
		if (restaurant.isOpen()){
			order.setOrderStatus("Submitted");
			DBManager.saveModel(order);
			OrderHistory orderHistory = restaurant.getOrders();
			if(orderHistory == null){
				orderHistory = new OrderHistory();
			}
			orderHistory.addOrder(order);
		}
		else{
			System.out.println("Restaurant is currently closed. Order not submitted.");
		}
	}
	
	@PostMapping("/manageOrder/cancel")
	public ModelAndView cancel(){
		cancelOrder();		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/"));
	}	
	public void cancelOrder(){
		order.setOrderStatus("Cancelled");
		DBManager.saveModel(order);
	}

	@PostMapping("/manageOrder/save")
	public ModelAndView save(Order order){
		saveOrder(order);		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
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

	@PostMapping("/manageOrder/add")
	public ModelAndView add(CreateOrderItem orderItem, Model model){
		MenuItem item = new MenuItem(orderItem.getDescription(),orderItem.getDescription() , 3.99, null);
		addToOrder(item);		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}		
	public void addToOrder(MenuItem menuItem){
		OrderItem orderItem = new OrderItem(menuItem);
		order.addItem(orderItem);
		DBManager.saveModel(orderItem);
		DBManager.saveModel(order);
		
	}

	@PostMapping("/manageOrder/remove")
	public ModelAndView remove(OrderItem orderItem, Model model){
		removeFromOrder(orderItem);		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}		
	public void removeFromOrder(OrderItem orderItem){
		order.removeItem(orderItem);
		//DBManager.saveModel(orderItem.getMenuItem());
		DBManager.saveModel(orderItem);
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
