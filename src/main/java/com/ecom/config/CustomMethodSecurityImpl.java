package com.ecom.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ecom.userdetails.UserAdapter;
/**
 *  Custom Method Level Security Expressions.
 *  <p> boolean method expressions which used for rest end points authorization.
 * @author suresh
 * @since 1.0
 * @version 1.0
 */
@Component("customMethodSecurity")
public class CustomMethodSecurityImpl implements CustomMethodSecurity{

	/**
	 * <p> Used to validate that, user can access own information.
	 * <p> LoggedIn user and param User id should be same.
	 * @param auth 
	 * @param userId
	 * @return {@link boolean}
	 */
	@Override
	public boolean validUserIdAccess(Authentication auth, Long userId) {
		UserDetails userDetails =(UserDetails) auth.getPrincipal();
		UserAdapter adapter = (UserAdapter)userDetails;
		
		return adapter.id().equals(userId);
	}

}
