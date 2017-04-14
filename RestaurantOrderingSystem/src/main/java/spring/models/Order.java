package spring.models;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Order implements Cloneable{
	
	private int orderId;
	
	private List<OrderItem> orderItems;
	
	private String orderStatus;
	
	private Date orderDate;
	
	public Order(){
		//set OrderId
		this.orderItems = new ArrayList<OrderItem>();
		orderStatus = "ACTIVE";
		orderDate = Calendar.getInstance().getTime();
	}
	
	public int getOrderId(){
		return orderId;
	}
	
	public void setorderId(int orderId){
		this.orderId = orderId;
	}
	
	public void addItem(OrderItem orderItem){
		//TODO ADD CODE
	}
	
	public void removeItem(OrderItem orderItem){
		//TODO ADD CODE
	}
	
	public double calculateOrderTotal(){
		//TODO ADD CODE
		return 0.00;
	}
	
	public String getOrderStatus(){
		return orderStatus;
	}
	
	public void setOrderStatus(String status){
		this.orderStatus = status;
	}
	
	public Date getOrderDate(){
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate){
		this.orderDate = orderDate;
	}
	
	public OrderMemento createMemento(){
		//TODO ADD CODE
		return null;
	}
	
	public void setMemento(OrderMemento orderMemento){
		//TODO ADD CODE
	}
	
	public Order clone(){
		Order clone = new Order();
		clone.setorderId(this.orderId);
		clone.setOrderDate(this.orderDate);
		clone.setOrderStatus(this.orderStatus);
		for(OrderItem item : this.orderItems){
			clone.addItem(item);
		}
		
		return clone;
	}


}
