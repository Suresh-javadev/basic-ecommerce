package com.ecom;


import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.modal.product.Product;
import com.ecom.services.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper jsonmapper=new ObjectMapper();
	
	private final String ADMIN_USERNSME="admin";
	private final String ADMIN_PASSWORD="admin@123";
	
	@MockBean
	private ProductService productService;
	
	/**
	 * <p> TEST Create Product API: "{@code /product}"
	 * <p> method POST
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void createProductWithAdminAuthPositiveTest() throws Exception {
		CreateProduct createDto =new CreateProduct("Product1", "P2", BigDecimal.valueOf(1000.00), "descriptions");
		
		Product product =new Product(1l);
		product.setName("Product1");
		
		when(productService.create(createDto)).thenReturn(product);
		
		this.mockMvc.perform(post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmapper.writeValueAsString(createDto))					
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isCreated())
		.andExpect(content().string(containsString("Product1")));
	}
	
	/**
	 * <p> TEST Create Product API: "{@code /product}"
	 * <p> method POST
	 * <p> Validation test Negative
	 * @throws Exception
	 */
	@Test
	void createProductWithAdminAuthValidationTest() throws Exception {
		CreateProduct createDto =new CreateProduct("P", "P2", BigDecimal.valueOf(1000.00), "descriptions");
		
		Product product =new Product(1l);
		product.setName("Product1");
		
		when(productService.create(createDto)).thenReturn(product);
		
		this.mockMvc.perform(post("/product")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonmapper.writeValueAsString(createDto))			
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isBadRequest())
		.andExpect(content().string(containsString("Validation Error")));
	}	
	
	@Test
	void getProductWithAdminAuthTest() throws Exception {
		Product product =new Product(1l);
		product.setName("Product1");
		
		when(productService.findById(1l)).thenReturn(product);
		
		this.mockMvc.perform(get("/product/1")
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(content().string(containsString("Product1")));
	}
	
	@Test
	void getProductWithAdminAuthNotFoundTest() throws Exception {

		when(productService.findById(1l)).thenThrow(new ResourceNotFoundException("Product not found"));
		
		this.mockMvc.perform(get("/product/1")
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isNotFound())
		.andExpect(content().string(containsString("Product not found")));
	}
}
