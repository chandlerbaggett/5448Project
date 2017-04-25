package spring.models;

import javax.persistence.Embeddable;

@Embeddable
public class Permission {
	private boolean canMakeOrders;
	private boolean canViewRestaurantOrders;
	private boolean canEditRestaurantStaff;
	private boolean canEditRestaurantMenu;
	
	public Permission() {
		super();
	}

	public Permission(boolean canMakeOrders, boolean canViewRestaurantOrders, boolean canEditRestaurantStaff,
			boolean canEditRestaurantMenu) {
		super();
		this.canMakeOrders = canMakeOrders;
		this.canViewRestaurantOrders = canViewRestaurantOrders;
		this.canEditRestaurantStaff = canEditRestaurantStaff;
		this.canEditRestaurantMenu = canEditRestaurantMenu;
	}

	public boolean canMakeOrders() {
		return canMakeOrders;
	}
	
	public boolean canViewRestaurantOrders() {
		return canViewRestaurantOrders;
	}
	
	public boolean canEditRestaurantStaff() {
		return canEditRestaurantStaff;
	}
	
	public boolean canEditRestaurantMenu() {
		return canEditRestaurantMenu;
	}
}
