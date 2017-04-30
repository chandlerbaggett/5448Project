package spring.controllers;

import spring.models.Menu;
import spring.models.MenuItem;
import utils.DBManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String loadPage(Model model) {
		Menu menu = DBManager.getRestaurant().getMenu();

		model.addAttribute("lists", menu);

		return "viewMenu";
	}	

	public Menu viewMenu(){		
		return menu;
	}
	
	@PostMapping("/viewMenu/add")
	public ModelAndView addMenuItem(addMenuItem menuItem, Model model){
		createMenuItem(menuItem);
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/viewMenu/"));
	}

	@PostMapping("/viewMenu/remove")
	public ModelAndView removeMenuItem(removeMenuItem menuItem, Model model){
		removeMenuItem(menuItem);
		return new ModelAndView(new RedirectView("/RestaurantOrderingSystem/viewMenu/"));
	} 

	public void addItem(MenuItem item){
		menu.addMenuItem(item);
		DBManager.saveModel(menu)
	}
	
	public void removeItem(MenuItem item){
		Menu menu = DBManager.getRestaurant().getMenu();

		for (MenuItem item : Menu){
			if (item.getId() == item.getId()) {
				item = item.getMenuItem();
			}
		}
		menu.removeMenuItem(item);
		DBManager.saveModel(menu);
	}
	
	public void editMenuItem(MenuItem item){
		//TO DO
	}	
	
	
}
