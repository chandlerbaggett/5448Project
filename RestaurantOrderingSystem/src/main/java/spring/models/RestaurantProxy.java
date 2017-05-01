package spring.models;

import utils.DBManager;

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
		if (canManageRestaurant()) {
			restaurant.setName(name);
		}		
	}

	public String getLocation() {
		return restaurant.getLocation();
	}

	public void setLocation(String location) {
		if (canManageRestaurant()) {
			restaurant.setLocation(location);
		}
	}

	public Menu getMenu() {
		return restaurant.getMenu();
	}

	public void setMenu(Menu menu) {
		if (canManageRestaurant()) {
			restaurant.setMenu(menu);
		}
	}

	public StaffList getStaff() {
		return restaurant.getStaff();
	}

	public void setStaff(StaffList staffList) {
		if (canManageRestaurant()) {
			restaurant.setStaff(staffList);
		}
	}

	public boolean isOpen() {
		return restaurant.isOpen();
	}

	public void setIsOpen(boolean isOpen) {
		if (canManageRestaurant()) {
			restaurant.setIsOpen(isOpen);
		}
	}

	public OrderHistory getOrders() {
		return restaurant.getOrders();
	}

	public void setOrders(OrderHistory orderHistory) {
		if (canManageRestaurant()) {
			restaurant.setOrders(orderHistory);
		}
	}
	
	private boolean canManageRestaurant() {
		User requestingUser = DBManager.getLoggedInUser();
		
		Permission permission = restaurant.getStaff().getPermissionForStaff(requestingUser);
		
		if (permission != null) {
			return permission.canManageRestaurant();
		}
		
		return false;
	}
}
