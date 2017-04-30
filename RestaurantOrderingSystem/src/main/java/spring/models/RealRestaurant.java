package spring.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "RESTAURANTS")
public class RealRestaurant extends Model implements Restaurant {
	private String name;
	private String location;
	
	//TODO make real
	@Transient
	private Menu menu;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private StaffList staffList;
	private boolean isOpen;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private OrderHistory orderHistory;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public StaffList getStaff() {
		return staffList;
	}
	
	public void setStaff(StaffList staffList) {
		this.staffList = staffList;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public OrderHistory getOrders() {
		return orderHistory;
	}
	
	public void setOrders(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
}
