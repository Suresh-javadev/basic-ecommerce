package com.ecom.config;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceAlreadyExistException;
import com.ecom.modal.User;
import com.ecom.services.product.ProductService;
import com.ecom.services.user.UserService;
import com.ecom.types.Roles;
/**
 * <p> Initial Mock Data Insert To DB
 * <p> Insert Admin and User
 * <p> Product Insert
 * <p> Category Insert
 * @author suresh
 * @since 1.0
 * @version 1.0
 */

@Component
public class InitialDataSetupLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup) {
			return;
		}
		
		createUserIfOnExist("Suresh Kumar Admin", "admin", "admin@gmail.com", "admin@123", Roles.ADMIN);
		createUserIfOnExist("Suresh Kumar User", "user", "user@gmail.com", "user@123", Roles.USER);
		
		try {
			productService.create(new CreateProduct("PS5 Console", "P1",BigDecimal.valueOf(50000.00) ,"PS5 is latest gaming console."));
			productService.createCategory(new CreateCategory("Gaming", "Gaming Category"));
		}catch(ResourceAlreadyExistException e) {}
		
		alreadySetup = true;
	}

	private void createUserIfOnExist(String name,String username,String email,String password,Roles role) {
		Optional<User> ops=userService.findUserByUsername(username);
		
		if(ops.isEmpty()) {
			CreateUserDto user=new CreateUserDto(name, email, username, password, role);
			userService.create(user);
		}
	}
}
