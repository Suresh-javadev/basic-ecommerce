package com.ecom.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecom.modal.User;

public class UserAdapter implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient User user;
	
	public UserAdapter(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<>();		
		authority.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
		return authority;
	}

	public Long id() {
		return this.user.getId();
	}
	
	@Override
	public String getPassword() {
		
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return this.user.isCredentialsNonExpire();
	}

	@Override
	public boolean isEnabled() {
		
		return this.user.isEnabled();
	}

}
