package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public void markOrderCompleted(Order order, Model model){
		order = (Order) DBManager.getModel(Order.class, order.getId());
		
		order.setOrderStatus("Completed");
		DBManager.saveModel(order);
	}
}
