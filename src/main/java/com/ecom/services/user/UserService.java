package com.ecom.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.UpdateUserDto;
import com.ecom.exception.UserAlreadyExistException;
import com.ecom.modal.User;

public interface UserService {

	public Optional<User> findUserByUsername(String username);
	public User findUserById(Long userId) throws UsernameNotFoundException;
	public User create(CreateUserDto user)throws UserAlreadyExistException;
	public List<User> findAll();
	public void delete(Long id);
	public User update(Long id,UpdateUserDto user)throws UsernameNotFoundException;
}
