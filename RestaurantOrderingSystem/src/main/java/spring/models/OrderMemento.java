package spring.models;

import java.util.ArrayList;
import java.util.List;

public class OrderMemento {

	private Integer orderId;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	private String orderStatus;
	
	private long orderDate;

	public OrderMemento(Integer orderId, long orderDate, String orderStatus, List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		for(OrderItem item: orderItems){
			OrderItem newItem = new OrderItem();
			newItem.setMenuItem(item.getMenuItem());
			newItem.setQuantity(item.getQuantity());
			this.orderItems.add(newItem);
		}

	}

	public OrderMemento() {
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}

}
