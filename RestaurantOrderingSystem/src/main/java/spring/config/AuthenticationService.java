package spring.config;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//TODO add actual database access for getting user
		if (username.equals("username")) {
			spring.models.User userInfo = new spring.models.User();
			userInfo.setUserName("username");
			userInfo.setPassword("password");
			
			GrantedAuthority authority = new SimpleGrantedAuthority("USER");
			UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
			return userDetails;
			
		} else {
			UsernameNotFoundException excpt = new UsernameNotFoundException(username);
			throw excpt;
		}
		
		
	}
} 
