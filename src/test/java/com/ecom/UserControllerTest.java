package com.ecom;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.UpdateUserDto;
import com.ecom.exception.UserNotFoundException;
import com.ecom.mapper.UserMapper;
import com.ecom.modal.User;
import com.ecom.services.user.UserService;
import com.ecom.types.Roles;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserMapper userMapper;
	
	private ObjectMapper jsonmapper=new ObjectMapper();
	
	private final String ADMIN_USERNSME="admin";
	private final String ADMIN_PASSWORD="admin@123";
	
	@MockBean
	private UserService userService;

	/**
	 * <p> TEST GET For User API: "{@code /user/userId}"
	 * <p> method GET
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void getUserWithAdminAuthPositiveTest() throws Exception {
		when(userService.findUserByUsername(ADMIN_USERNSME)).thenReturn(Optional.of(userStubAdmin(ADMIN_USERNSME)));
		when(userService.findUserById(1l)).thenReturn(userStubAdmin(ADMIN_USERNSME));
		
		this.mockMvc.perform(get("/user/1")
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(content().string(containsString(ADMIN_USERNSME)));
	}
	
	/**
	 * <p> Test GET For User API: "{@code /user/userId}"
	 * <p> method GET
	 * <p> Negative test - User not found by id
	 * @throws Exception
	 */
	@Test
	void getUserWithAdminAuthNegativeNotFoundTest() throws Exception {
		when(userService.findUserByUsername(ADMIN_USERNSME)).thenReturn(Optional.of(userStubAdmin(ADMIN_USERNSME)));
		
		when(userService.findUserById(3l)).thenThrow(new UserNotFoundException("User Not Found"));
		
		this.mockMvc.perform(get("/user/3")
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isNotFound())
		.andExpect(content().string(containsString("User Not Found")));
	}
	
	/**
	 * <p> Test Create for User API: "{@code /user}"
	 * <p> method POST
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void createUserWithAdminAuthPositiveTest() throws Exception {
		
		CreateUserDto userDto=new CreateUserDto("Test User", "test@gmail.com", "testuser", "testpass", Roles.USER);
		User entity = userMapper.createUserDtoToUserModal(userDto);
	
		when(userService.findUserByUsername(ADMIN_USERNSME)).thenReturn(Optional.of(userStubAdmin(ADMIN_USERNSME)));
		//mocking userRepo
		when(userService.create(userDto)).thenReturn(entity);
		
		this.mockMvc.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonmapper.writeValueAsString(userDto))
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isCreated())
		.andExpect(content().string(containsString("Test User")));
	}
	
	/**
	 * <p> Test Create For User API: "{@code /user}"
	 * <p> method POST
	 * <p> Negative test - Email Not Present
	 * @throws Exception
	 */
	@Test
	void createUserWithAdminAuthNegativeTest() throws Exception {
		
		CreateUserDto userDto=new CreateUserDto("Test User", "", "testuser", "testpass", Roles.USER);
		User entity = userMapper.createUserDtoToUserModal(userDto);
	
		when(userService.findUserByUsername(ADMIN_USERNSME)).thenReturn(Optional.of(userStubAdmin(ADMIN_USERNSME)));
		//mocking userRepo
		when(userService.create(userDto)).thenReturn(entity);
		
		this.mockMvc.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonmapper.writeValueAsString(userDto))
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isBadRequest())
		.andExpect(content().string(containsString("Validation Error")));
	}	
	
	
	/**
	 * <p> Test Update for User API: "{@code /user/userId}"
	 * <p> method DELETE
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void updateUserWithAdminAuthPositiveTest() throws Exception {
		UpdateUserDto update=new UpdateUserDto();
		update.setName("sk c");
		update.setEmail("yes@gmail.com");
		
		when(userService.findUserByUsername(ADMIN_USERNSME)).thenReturn(Optional.of(userStubAdmin(ADMIN_USERNSME)));
		Long userId=5l;	
		
		when(userService.update(userId, update)).thenReturn(userStubAdmin(ADMIN_USERNSME));
		
		this.mockMvc.perform(patch("/user/"+userId)
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonmapper.writeValueAsString(update))				
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk());
	}
	
	
	/**delete
	 * <p> Test Delete for User API: "{@code /user/userId}"
	 * <p> method PATCH
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void deleteUserWithAdminAuthPositiveTest() throws Exception {
		when(userService.findUserByUsername(ADMIN_USERNSME)).thenReturn(Optional.of(userStubAdmin(ADMIN_USERNSME)));
			
		this.mockMvc.perform(delete("/user/1")
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(content().string(containsString("Deleted Successfully")));
	}
	
	
	private User userStubAdmin(String username) {
		User user=new User();
		user.setId(1l);
		user.setName("Suresh Kumar Admin");
		user.setEmail("admin@gmail.com");
		user.setUsername(username);
		user.setPassword("$2a$10$u4WRZPJmA1.jRm3qQuvIjeyttq3w6JXCxthyN31gixKd6th8k39Aq");
		user.setRole(Roles.ADMIN);
		return user;
	}
	
}
