package com.maiconspas.security;



import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maiconspas.entity.User;

/**
 * @author Maicon Santana
 *
 */
public class UserDetailsDecorator implements UserDetails {

	private static final long serialVersionUID = 833114823145835051L;

	public static final String ROLES_PREFIX = "ROLE_";

	    private User user;

	    public UserDetailsDecorator(User user) {
	        this.user = user;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return user.getRoles().stream().map(x->new SimpleGrantedAuthority(ROLES_PREFIX+x)).collect(Collectors.toList());
	    }

	    @Override
	    public String getPassword() {
	        return user.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return user.getUserName();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}
