package app.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CurrentUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

    public CurrentUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	List<String> auth = new ArrayList<String>();
    	for (String e : user.getRoles())
    		auth.add(e);
    	
    	List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    	
    	for(int i=0; i<auth.size(); i++) {
    		list.add(new SimpleGrantedAuthority(auth.get(i).toString()));
    	}
    	
        return list;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user.getUsername() +
                "} ";
    }

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
