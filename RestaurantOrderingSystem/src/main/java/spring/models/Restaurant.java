package spring.models;

public interface Restaurant {

	public String getName();
	public void setName(String name);
	
	public String getLocation();
	public void setLocation(String location);
	
	public Menu getMenu();
	public void setMenu(Menu menu);
	
	public StaffList getStaff();
	public void setStaff(StaffList staffList);
	
	
	public boolean isOpen();
	public void setIsOpen(boolean isOpen);

	public OrderHistory getOrders();
	public void setOrders(OrderHistory orderHistory);

}
