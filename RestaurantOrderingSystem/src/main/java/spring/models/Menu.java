package spring.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MENU")
public class Menu extends Model {
	@OneToMany(fetch = FetchType.EAGER)
	private Set<MenuItem> menuItems;
	
	public Menu() {
		super();
		menuItems = new HashSet<MenuItem>();
	}

	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
	
	public void removeMenuItem(MenuItem menuItem) {
		menuItems.remove(menuItem);
	}

	public Set<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
