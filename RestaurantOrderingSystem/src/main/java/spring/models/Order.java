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
		//set OrderId/ set by hibernate?
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
		
		if(orderItems.contains(orderItem)){
			int itemIndex = orderItems.indexOf(orderItem);
			int itemQuantity = orderItems.get(itemIndex).getQuantity();
			orderItems.get(itemIndex).setQuantity(itemQuantity+1);
			
			//or
			//orderItems.get(orderItems.indexOf(orderItem)).setQuantity(orderItems.get(orderItems.indexOf(orderItem)).getQuantity()+1);
		}
		else{
			orderItems.add(orderItem);
		}
		
	}
	
	public void removeItem(OrderItem orderItem){
		
		if(orderItems.contains(orderItem)){
			int itemIndex = orderItems.indexOf(orderItem);
			int itemQuantity = orderItems.get(itemIndex).getQuantity();
			
			if(itemQuantity > 1){
				orderItems.get(itemIndex).setQuantity(itemQuantity-1);
			}
			else{
				orderItems.remove(orderItem);
			}
			
		}
		
	}
	
	public double calculateOrderTotal(){
		double total = 0.00;
		
		for(OrderItem item : orderItems){				
			total += (item.getMenuItem().getPrice()*item.getQuantity());
		}	
		
		return  total;
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
	
	//save
	public OrderMemento createMemento(){
		return new OrderMemento(this);
	}
	
	//restore
	public void setMemento(OrderMemento memento){
		this.orderId = memento.getOrder().getOrderId();
		this.orderDate = memento.getOrder().getOrderDate();
		this.orderStatus = memento.getOrder().getOrderStatus();
		this.orderItems = memento.getOrder().getOrderItems();
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
	
	private List<OrderItem>  getOrderItems(){
		return this.orderItems;
	}


}
