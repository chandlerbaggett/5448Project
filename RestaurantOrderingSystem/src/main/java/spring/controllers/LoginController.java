package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.beans.LoginBean;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String init(Model model) {
        model.addAttribute("msg", "Please Enter Your Login Details");
        return "login";
    }
 
    @PostMapping("/login")
    public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean) {
        if (loginBean == null) {
        	 model.addAttribute("error", "Please enter Details");
             return "login";
        }
        
        if (verifyLogin(loginBean)) {
        	return "home";
        }
        
        model.addAttribute("error", "Invalid Details");
        return "login";
    }

	private boolean verifyLogin(LoginBean loginBean) {
		String userName = loginBean.getUserName();
		String password = loginBean.getPassword();
		if (userName == null || password == null) {
			return false;
		}
		
		// TODO attempt to fetch user from DB and verify password
		if (userName.equals("root") && password.equals("password")) {
			return true;
		}
		
		return false;
	}
}