package com.ecom.config;

import org.springframework.security.core.Authentication;

import com.ecom.dto.order.response.OrderResponseDto;

public interface CustomMethodSecurity {

	public boolean validUserIdAccess(Authentication auth, Long userId);
	
	public boolean validOrderIdAccess(Authentication auth,OrderResponseDto orderDto);
}
