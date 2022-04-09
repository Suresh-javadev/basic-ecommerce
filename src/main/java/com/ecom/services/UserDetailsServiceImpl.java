package com.ecom.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.modal.User;
import com.ecom.services.user.UserService;
import com.ecom.userdetails.UserAdapter;

/**
 * <p> This is custom implementation of UserDetails for Spring Security.
 * <p> Spring will depend on this implementation for fetching user details.
 * @author suresh
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOps = userService.findUserByUsername(username);
		
		if(userOps.isEmpty())
			throw new UsernameNotFoundException("User not found by username: "+username);
		
		return new UserAdapter(userOps.get());
	}

}
