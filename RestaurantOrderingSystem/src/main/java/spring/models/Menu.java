package spring.models;

import java.util.ArrayList;
import java.util.Collection;

public class Menu {
	private Collection<MenuItem> menuItems;
	
	public Menu() {
		super();
		menuItems = new ArrayList<MenuItem>();
	}

	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}
	
	public void RemoveMenuItem(MenuItem menuItem) {
		menuItems.remove(menuItem);
	}
}
