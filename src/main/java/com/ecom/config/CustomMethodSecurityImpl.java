package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ecom.dto.order.response.OrderResponseDto;
import com.ecom.services.order.OrderService;
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

	@Autowired
	private OrderService orderService;
	
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

	/**
	 * <p> Used to validate that, user can access only order he owns.
	 * <p> LoggedIn user and param User id should be same.
	 * @param auth 
	 * @param order
	 * @return {@link boolean}
	 */
	@Override
	public boolean validOrderIdAccess(Authentication auth, OrderResponseDto orderDto) {
		UserDetails userDetails =(UserDetails) auth.getPrincipal();
		UserAdapter adapter = (UserAdapter)userDetails;
		
		if(orderDto == null)
			return false;
		
		return adapter.id().equals(orderDto.getUserId());
	}

	@Override
	public boolean validOrderIdAccess(Authentication auth, Long orderId) {
		OrderResponseDto orderDto =orderService.findOrderById(orderId);
		return validOrderIdAccess(auth,orderDto);
	}

}
