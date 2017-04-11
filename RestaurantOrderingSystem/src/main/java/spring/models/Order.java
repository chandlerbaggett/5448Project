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
		this.orderItems = new ArrayList<OrderItem>;
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
		//ADD CODE
	}
	
	public void removeItem(OrderItem orderItem){
		//ADD CODE
	}
	
	public double calculateOrderTotal(){
		//ADD CODE
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
	
	public void setorderDate(Date orderDate){
		this.orderDate = orderDate;
	}
	
	public OrderMemento createMemento(){
		//ADD CODE
	}
	
	public void setMemento(OrderMemento orderMemento){
		//ADD CODE
	}
	
	public Order clone(){
		//ADD CODE
	}


}
