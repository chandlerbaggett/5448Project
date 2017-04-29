package spring.controllers;

import spring.models.Menu;
import spring.models.MenuItem;
import utils.DBManager;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
		DBManager.saveModel(menu)
	}
	
	public void removeItem(MenuItem item){
		menu.removeMenuItem(item);
		DBManager.saveModel(menu);
	}
	
	public void editMenuItem(MenuItem item){
		//TO DO
	}	
	
	
}
