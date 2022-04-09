package com.ecom.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.UpdateUserDto;
import com.ecom.modal.User;
import com.ecom.services.user.UserService;

/**
 * Rest End Point For User Related Operations
 * <p> Provide user create, fetch, delete, edit end points.
 * @author suresh
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserApi{
	
	@Autowired
	private UserService userService;
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<User> create(@RequestBody @Valid CreateUserDto user) {		
		User created=userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') or @customMethodSecurity.validUserIdAccess(authentication, #userId)")
	@GetMapping("/{userId}")
	public ResponseEntity<User> user(@PathVariable("userId") Long userId) {		
		return ResponseEntity.ok(userService.findUserById(userId));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<User>> users() {
		return ResponseEntity.ok(userService.findAll());
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> delete(@PathVariable("userId") Long userId) {	
		userService.delete(userId);
		return ResponseEntity.ok("Deleted Successfully");
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') or @customMethodSecurity.validUserIdAccess(authentication, #userId)")
	@PatchMapping("/update/{userId}")
	public ResponseEntity<User> update(@PathVariable("userId") Long userId,@RequestBody @Valid UpdateUserDto user) {	
		return ResponseEntity.ok(userService.update(userId,user));
	}

}
