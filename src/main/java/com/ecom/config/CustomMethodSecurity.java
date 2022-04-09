package com.ecom.config;

import org.springframework.security.core.Authentication;

public interface CustomMethodSecurity {

	public boolean validUserIdAccess(Authentication auth, Long userId);
}
