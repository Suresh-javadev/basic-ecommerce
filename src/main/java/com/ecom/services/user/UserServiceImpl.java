package com.ecom.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.UpdateUserDto;
import com.ecom.exception.UserAlreadyExistException;
import com.ecom.exception.UserNotFoundException;
import com.ecom.mapper.UserMapper;
import com.ecom.modal.User;
import com.ecom.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> findUserByUsername(String username) {		
		return userRepo.findByUsername(username);
	}

	@Override
	public User create(CreateUserDto user)throws UserAlreadyExistException {
		Assert.notNull(user, "The user must not be null");	
		Assert.notNull(user.getRole(), "Role must not be null");
		
		Optional<User> userOps = findUserByUsername(user.getUsername());
		
		if(userOps.isPresent())
			throw new UserAlreadyExistException("User already exists");
		
		//password encryption
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User toCreate=userMapper.createUserDtoToUserModal(user);
		return userRepo.save(toCreate);
	}

	@Override
	public User findUserById(Long userId) throws UsernameNotFoundException {
		
		Optional<User> userOps =userRepo.findById(userId);
		if(userOps.isEmpty())
			throw new UserNotFoundException("User not found");
		
		return userOps.get();
	}

	@Override
	public List<User> findAll() {	
		return userRepo.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User update(Long id, UpdateUserDto update)throws UsernameNotFoundException {
		Optional<User> userOps =userRepo.findById(id);
		
		if(userOps.isEmpty())
			throw new UserNotFoundException("User not found");
		
		User user = userOps.get();
		userMapper.updateEntity(user, update);
		
		return userRepo.save(user);
	}

}
