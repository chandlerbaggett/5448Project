package spring.models;

import java.awt.Image;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MENU_ITEMS")
public class MenuItem extends Model{
	
	private String name;
	
	private String description;
	
	private double price;
	
	//@Transient
	//private Image image;

	public MenuItem() {
		
	}

	public MenuItem(String name, String descroption, double price, Image image) {
		this.name = name;
		this.description = descroption;
		this.price = price;
		//this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescroption() {
		return description;
	}

	public void setDescroption(String descroption) {
		this.description = descroption;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
