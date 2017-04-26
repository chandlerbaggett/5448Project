package spring.models;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItem {

	private MenuItem menuItem;
	
	private int quantity = 1;
	
	public OrderItem() {
		this.menuItem = new MenuItem();
	}	

	public OrderItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
