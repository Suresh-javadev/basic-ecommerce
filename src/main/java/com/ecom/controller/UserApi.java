package com.ecom.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.ErrorDto;
import com.ecom.dto.UpdateUserDto;
import com.ecom.modal.User;

@Api
public interface UserApi {

	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "User Created",response = User.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create User")
	public ResponseEntity<User> create(CreateUserDto user);
	
	@ApiOperation(value = "Get user by id", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<User> user(@ApiParam(required = true,example = "1",type = "Long") Long userId);
	
	@ApiOperation(value = "Get list of users", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<List<User>> users();
	
	@ApiOperation(value = "Delete user by id", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleted Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<String> delete(@ApiParam(required = true,example = "1",type = "Long") Long userId);
	
	
	@ApiOperation(value = "Upate user by id", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Updated Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<User> update(@ApiParam(required = true,example = "1",type = "Long") Long userId,UpdateUserDto user);
	
}
