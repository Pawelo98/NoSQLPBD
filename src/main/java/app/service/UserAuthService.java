package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.entity.CurrentUser;
import app.entity.User;

@Service
public class UserAuthService implements UserDetailsService {
	
	private final UserService userService;
	
	@Autowired
    public UserAuthService(UserService userService) {
        this.userService = userService;
    }

	@Override
	public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUser(username);
		if(user==null) {
			throw new UsernameNotFoundException("Brak u¿ytkownika");
		} else {
			return new CurrentUser(user);
		}
	}

}
