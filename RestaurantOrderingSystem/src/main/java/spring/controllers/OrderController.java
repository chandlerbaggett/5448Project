package spring.controllers;

import java.text.DecimalFormat;
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
	private OrderMemento orderMemento = new OrderMemento();
	
	
	@GetMapping("/manageOrder")
	public String loadPage(Model model) {
		restaurant = DBManager.getRestaurant();
		restaurant.setIsOpen(true);
		
		if (DBManager.getActiveOrder() == null || !DBManager.getActiveOrder().getOrderStatus().equals("ACTIVE")){
			buildBasicOrder();
		}
		else{
			order = DBManager.getActiveOrder();
		}
		model.addAttribute("order", order);
		model.addAttribute("items", order.getOrderItems() );
		return "manageOrder";
	}
	
	private void buildBasicOrder(){
		order = createOrder();
		addToOrder(new MenuItem("Free Chips and Salsa", "Free Chips and Salsa Appetizer", 0.00,null));
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
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/home"));
	}
	
	public void submitOrder(){
		restaurant.setIsOpen(true);
		if (restaurant.isOpen()){
			order.setOrderStatus("SUBMITTED");
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
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/home"));
	}	
	public void cancelOrder(){
		order.setOrderStatus("CANCELLED");
		DBManager.saveModel(order);
	}

	@PostMapping("/manageOrder/save")
	public ModelAndView save(){
		saveOrder();		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}		
	public void saveOrder(){		
		orderMemento = order.createMemento();
		DBManager.saveModel(order);
	}
	
	@PostMapping("/manageOrder/resume")
	public ModelAndView resume(){
		Order newOrder = new Order();
		newOrder = resumeOrder();	
		this.order = newOrder;
		DBManager.saveModel(order);	
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}		
	public Order resumeOrder(){		
		
		/*for(OrderItem item: order.getOrderItems()){
			DBManager.deleteModel(item);
		}*/ //don't delete OrderItems manually when cascadetype is ALL from Order to OrderItem
		order.setMemento(orderMemento);//restore from memento
		return order;
	}

	@PostMapping("/manageOrder/add")
	public ModelAndView add(CreateOrderItem orderItem, Model model){
		MenuItem menuItem;	
		if (DBManager.getMenuItem(orderItem.getDescription()) == null){
			menuItem = new MenuItem(orderItem.getDescription(),orderItem.getDescription() , 3.99, null);
		}
		else{
			menuItem = DBManager.getMenuItem(orderItem.getDescription());
		}
		
		addToOrder(menuItem);		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}		
	public void addToOrder(MenuItem menuItem){
		OrderItem orderItem = order.addItem(menuItem);
		DBManager.saveModel(menuItem);	
		DBManager.saveModel(order);
	}

	@PostMapping("/manageOrder/remove")
	public ModelAndView remove(int id, Model model){
		OrderItem orderItem = (OrderItem) DBManager.getModel(OrderItem.class, id);
		System.out.println("Calling removeFromOrder on "+orderItem);
		removeFromOrder(orderItem);	
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}		
	public void removeFromOrder(OrderItem orderItem){
		int itemIndex = order.getOrderItems().indexOf(orderItem);
		int itemQuantity = order.getOrderItems().get(itemIndex).getQuantity();
		if(itemQuantity <= 1){	
			DBManager.deleteModel(orderItem);
		}
		order.removeItem(orderItem);
		DBManager.saveModel(order);
	}
	
	public double displayTotal(Order order){
		return order.calculateOrderTotal();
	}
	

	@PostMapping("/manageOrder/duplicate")
	public ModelAndView duplicate(){
		order = duplicateOrder();		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/manageOrder/"));
	}	
	public Order duplicateOrder(){
		Order duplicate = order.clone();
		duplicate.setOrderStatus("ACTIVE");
		DBManager.saveModel(duplicate);
		return duplicate;
	}
	

}
