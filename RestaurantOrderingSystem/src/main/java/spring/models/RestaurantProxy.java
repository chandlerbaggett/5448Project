package spring.models;

public class RestaurantProxy implements Restaurant {
	private RealRestaurant restaurant = new RealRestaurant();

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLocation(String location) {
		// TODO Auto-generated method stub
		
	}

	public Menu getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	public StaffList getStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStaff(StaffList staffList) {
		// TODO Auto-generated method stub
		
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return restaurant.isOpen();
	}

	public void setIsOpen(boolean isOpen) {
		restaurant.setIsOpen(isOpen);
	}

	public OrderHistory getOrders() {
		return restaurant.getOrders();
	}

	public void setOrders(OrderHistory orderHistory) {
		restaurant.setOrders(orderHistory);
		
	}
}
