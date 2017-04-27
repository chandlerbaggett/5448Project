package spring.models;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem extends Model {
	
	//@Transient
	@OneToOne(cascade = {CascadeType.ALL})
	private MenuItem menuItem;
	
	private int quantity = 1;
	
	public OrderItem() {
		super();
		this.menuItem = new MenuItem();
	}	

	public OrderItem(MenuItem menuItem) {
		super();
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
