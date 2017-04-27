package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ComponentScan("spring.config")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationService authenticationService;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         	.authorizeRequests()
         	.antMatchers("/createAccount").anonymous()
            .anyRequest().authenticated()
            .and()
            .formLogin().defaultSuccessUrl("/welcome", true)
            .loginPage("/login")
            .permitAll()
           
        
            .and()
            .csrf().disable();
//            .and().regexMatcher("createAccount").anonymous();
        
	}
    
    
}