package com.ecom.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ecom.dto.CreateUserDto;
import com.ecom.modal.User;
import com.ecom.services.user.UserService;
import com.ecom.types.Roles;

@Component
public class InitialDataSetupLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup) {
			return;
		}
		
		createUserIfOnExist("Suresh Kumar Admin", "admin", "admin@gmail.com", "admin@123", Roles.ADMIN);
		createUserIfOnExist("Suresh Kumar User", "user", "user@gmail.com", "user@123", Roles.USER);
		
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
