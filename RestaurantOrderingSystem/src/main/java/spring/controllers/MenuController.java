package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.models.Menu;
import spring.models.MenuItem;
import utils.DBManager;

@Controller
public class MenuController {

	private Menu menu = new Menu();
	
	public void createMenu(){
		this.menu = new Menu();	
		DBManager.saveModel(menu);
	}
	
	@GetMapping("/viewMenu")	
	public Menu viewMenu(){		
		return menu;
	}
	
	public void addItem(MenuItem item){
		menu.addMenuItem(item);
		DBManager.saveModel(menu);
	}
	
	public void removeItem(MenuItem item){
		menu.removeMenuItem(item);
		DBManager.saveModel(menu);
	}
	
	public void editMenuItem(MenuItem item){
		//TO DO
	}	
	
	
}
