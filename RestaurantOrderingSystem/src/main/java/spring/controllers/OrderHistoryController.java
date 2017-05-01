package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import spring.models.Order;
import spring.models.OrderHistory;
import spring.models.User;
import utils.DBManager;

@Controller
public class OrderHistoryController {
	private OrderHistory orderHistory;
	@GetMapping("/user_order_history")
	public String loadUserPage(Model model) {
		orderHistory = DBManager.getLoggedInUser().getOrderHistory();
		
		model.addAttribute("orders", orderHistory.getOrders());
		return "orderHistory";
	}
	
	@GetMapping("/restaurant_order_history")
	public String loadRestaurantPage(Model model) {
		orderHistory = DBManager.getRestaurant().getOrders();
		
		model.addAttribute("orders", orderHistory.getOrders());
		model.addAttribute("isRestaurant", true);
		return "orderHistory";
	}
	
	@PostMapping("/order/complete")
	public ModelAndView markOrderCompleted(Order order, Model model){
		order = (Order) DBManager.getModel(Order.class, order.getId());
		
		order.setOrderStatus("Completed");
		DBManager.saveModel(order);
		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/restaurant_order_history"));
	}
	
	@PostMapping("/order/duplicate")
	public ModelAndView duplicateOrder(Order order, Model model){
		order = (Order) DBManager.getModel(Order.class, order.getId());
		
		Order newOrder = order.clone();
		newOrder.setOrderStatus("SUBMITTED");
		DBManager.saveModel(newOrder);
		
		OrderHistory history = DBManager.getLoggedInUser().getOrderHistory();
		history.addOrder(newOrder);
		
		DBManager.saveModel(history);
		
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/user_order_history"));
	}
}
