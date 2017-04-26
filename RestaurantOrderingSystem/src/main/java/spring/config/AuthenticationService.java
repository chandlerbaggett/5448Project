package spring.config;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.models.OrderHistory;
import spring.models.User;
import utils.DBManager;

@Service
public class AuthenticationService implements UserDetailsService {
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (DBManager.getModel(User.class, 1) == null) {
			User user = new User();
			user.setUserName("username");
			user.setPassword("password");
			
			user.setDisplayName("username");
			user.setOrderHistory(new OrderHistory());
			
			DBManager.saveModel(user);
		};
		
		User user = DBManager.getUser(username);
		
		if (user != null) {	
			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
			UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Arrays.asList(authority));
			return userDetails;
			
		} else {
			UsernameNotFoundException excpt = new UsernameNotFoundException(username);
			throw excpt;
		}
	}
} 
