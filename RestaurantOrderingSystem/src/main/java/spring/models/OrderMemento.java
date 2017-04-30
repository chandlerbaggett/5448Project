package spring.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

public class OrderMemento {

	private Integer orderId;
	
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	private String orderStatus;
	
	private Date orderDate;

	public OrderMemento(Integer orderId, Date orderDate, String orderStatus, List<OrderItem> orderItems) {
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
