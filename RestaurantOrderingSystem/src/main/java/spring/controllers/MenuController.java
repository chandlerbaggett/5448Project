package spring.controllers;

import spring.models.Menu;
import spring.models.MenuItem;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.ui.Model;

@Controller
public class MenuController {

	private Menu menu;
	
	public void createMenu(){
		this.menu = new Menu();	
	}
	
	public Menu viewMenu(){		
		return menu;
	}
	
	public void addItem(MenuItem item){
		menu.addMenuItem(item);
		
	}
	
	public void removeItem(MenuItem item){
		menu.removeMenuItem(item);
	}
	
	public void editMenuItem(MenuItem item){
		//TO DO
	}	
	
	
}
