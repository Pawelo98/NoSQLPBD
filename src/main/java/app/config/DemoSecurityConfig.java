package app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.service.UserAuthService;
import app.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private DataSource securityDataSource;
	
	@Autowired
	private UserAuthService userAuthService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// z bazy
		//auth.jdbcAuthentication().dataSource(securityDataSource);
		
		//nowe pomys³y
		auth
        .userDetailsService(userAuthService)
        .passwordEncoder(new BCryptPasswordEncoder());
		
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("{noop}admin").roles("ADMIN","ADMINISTRATIVE","PHYSICAL");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				.and()
				.logout().permitAll();
		
	}
		
}






