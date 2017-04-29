package spring.models;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem extends Model {
	
	//@Transient
	//@OneToOne(cascade = {CascadeType.ALL})
	@OneToOne(fetch = FetchType.EAGER)
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuItem == null) ? 0 : menuItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;			
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		OrderItem other = (OrderItem) obj;
		if (menuItem == null) {
			if (other.menuItem != null){
				return false;
			}
		} else if (!menuItem.equals(other.menuItem)){
			return false;
		}
		return true;
	}

}
