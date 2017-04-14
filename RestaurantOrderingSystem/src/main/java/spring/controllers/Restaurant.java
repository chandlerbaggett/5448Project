package spring.controllers;

import spring.models.OrderHistory;

public interface Restaurant {

	public String getName();
	public void setName(String name);
	
	public String getLocation();
	public String setLocation(String location);
	
	//TODO implement classes
//	public Menu getMenu();
//	public void setMenu(Menu menu);
	
	//TODO implement classes
//	public StaffList getStaff();
//	public void setStaff(StaffList staffList);
	
	
	public boolean isOpen();
	public void setIsOpen(boolean isOpen);

	public OrderHistory getOrders();
	public void setOrders(OrderHistory orderHistory);

}
