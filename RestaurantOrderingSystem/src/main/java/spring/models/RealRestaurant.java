package spring.models;

public class RealRestaurant implements Restaurant {
	private String name;
	private String location;
	private Menu menu;
	private StaffList staffList;
	private boolean isOpen;
	private OrderHistory orderHistory;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getLocation() {
		return location;
	}
	
	@Override
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public Menu getMenu() {
		return menu;
	}
	
	@Override
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public StaffList getStaff() {
		return staffList;
	}
	
	@Override
	public void setStaff(StaffList staffList) {
		this.staffList = staffList;
	}
	
	@Override
	public boolean isOpen() {
		return isOpen;
	}
	
	@Override
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	@Override
	public OrderHistory getOrders() {
		return orderHistory;
	}
	
	@Override
	public void setOrders(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
}
