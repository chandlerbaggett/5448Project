package spring.models;

public class RestaurantProxy implements Restaurant {
	private RealRestaurant restaurant = new RealRestaurant();

	public RestaurantProxy(RealRestaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public String getName() {
		return restaurant.getName();
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public String getLocation() {
		return restaurant.getLocation();
	}

	public void setLocation(String location) {
		// TODO Auto-generated method stub
		
	}

	public Menu getMenu() {
		return restaurant.getMenu();
	}

	public void setMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	public StaffList getStaff() {
		return restaurant.getStaff();
	}

	public void setStaff(StaffList staffList) {
		// TODO Auto-generated method stub
	}

	public boolean isOpen() {
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
