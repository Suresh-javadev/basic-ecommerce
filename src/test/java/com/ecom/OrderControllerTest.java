package com.ecom;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.CreateOrderDetails;
import com.ecom.dto.order.response.OrderResponseDto;
import com.ecom.services.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper jsonmapper=new ObjectMapper();
	
	private final String ADMIN_USERNSME="admin";
	private final String ADMIN_PASSWORD="admin@123";
	
	@MockBean
	private OrderService orderService;
	
	/**
	 * <p> TEST Create Order API: "{@code /order}"
	 * <p> method POST
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void createOrderWithAdminAuthPositiveTest() throws Exception {
		CreateOrder createDto=new CreateOrder();
		createDto.setAmount(BigDecimal.valueOf(100.00));
		createDto.setEmail("suresh@gmail.com");
		createDto.setUserId(1l);
		
		CreateOrderDetails createOrderDetails = new CreateOrderDetails();
		createOrderDetails.setProductId(1l);
		createOrderDetails.setCount((short)1);
		
		createDto.setOrderDetails(Arrays.asList(createOrderDetails));
		
		OrderResponseDto order=new OrderResponseDto();
		order.setEmail("suresh@gmail.com");
		
		when(orderService.createOrder(createDto)).thenReturn(order);
		
		this.mockMvc.perform(post("/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmapper.writeValueAsString(createDto))					
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isCreated())
		.andExpect(content().string(containsString("suresh@gmail.com")));
	}
	
	/**
	 * <p> TEST Create Order API: "{@code /order}"
	 * <p> method POST
	 * <p> Validation test Negative
	 * @throws Exception
	 */
	@Test
	void createOrderWithAdminAuthValidationTest() throws Exception {
		CreateOrder createDto=new CreateOrder();
		createDto.setAmount(BigDecimal.valueOf(100.00));
		createDto.setEmail("suresh@gmail.com");
		createDto.setUserId(1l);
		
		OrderResponseDto order=new OrderResponseDto();
		order.setEmail("suresh@gmail.com");
		
		when(orderService.createOrder(createDto)).thenReturn(order);
		
		this.mockMvc.perform(post("/order")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonmapper.writeValueAsString(createDto))			
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isBadRequest())
		.andExpect(content().string(containsString("Order Details Required!")));
	}	

}
