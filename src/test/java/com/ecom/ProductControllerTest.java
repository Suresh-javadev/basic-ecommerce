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

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.modal.product.Category;
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
	
	private final String USER_USERNSME="user";
	private final String USER_PASSWORD="user@123";
	
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
	
	@Test
	void updateProductWithAdminAuthNotFoundTest() throws Exception {

		CreateProduct createDto =new CreateProduct("Product1", "P2", BigDecimal.valueOf(1000.00), "descriptions");
		
		when(productService.update(1l, createDto)).thenThrow(new ResourceNotFoundException("Product not found"));
		
		this.mockMvc.perform(put("/product/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmapper.writeValueAsString(createDto))				
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isNotFound())
		.andExpect(content().string(containsString("Product not found")));
	}
	
	@Test
	void updateProductWithAdminAuthTest() throws Exception {

		CreateProduct createDto =new CreateProduct("Product1", "P2", BigDecimal.valueOf(1000.00), "descriptions");
		
		Product product =new Product(1l);
		product.setName("Product1");
		
		when(productService.update(1l, createDto)).thenReturn(product);
		
		this.mockMvc.perform(put("/product/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmapper.writeValueAsString(createDto))	
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(content().string(containsString("Product1")));
	}
	
	/**
	 * <p> TEST Create Category API: "{@code /product/category}"
	 * <p> method POST
	 * <p> Positive test
	 * @throws Exception
	 */
	@Test
	void createCategoryWithAdminAuthPositiveTest() throws Exception {
		CreateCategory createDto=new CreateCategory("Cat1", "Test Category");
		
		Category cat=new Category();
			cat.setName("Cat1");
		
		when(productService.createCategory(createDto)).thenReturn(cat);
		
		this.mockMvc.perform(post("/product/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonmapper.writeValueAsString(createDto))					
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isCreated())
		.andExpect(content().string(containsString("Cat1")));
	}
	
	/**
	 * <p> TEST Create Product API: "{@code /product/category}"
	 * <p> method POST
	 * <p> Validation test Negative
	 * @throws Exception
	 */
	@Test
	void createCategoryWithAdminAuthValidationTest() throws Exception {
		CreateCategory createDto=new CreateCategory("C", "Test Category");
		
		Category cat=new Category();
			cat.setName("Cat1");
		
		when(productService.createCategory(createDto)).thenReturn(cat);
		
		this.mockMvc.perform(post("/product/category")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonmapper.writeValueAsString(createDto))			
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isBadRequest())
		.andExpect(content().string(containsString("Validation Error")));
	}
	
	@Test
	void listProductWithAdminAuthValidationTest() throws Exception {
		Product p1=new Product();
		p1.setId(1l);
		
		Product p2=new Product();
		p2.setId(2l);
		when(productService.list()).thenReturn(Arrays.asList(p1,p2));
		
		this.mockMvc.perform(get("/products")		
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(jsonPath("$.length()").value(2));
	}
		
	@Test
	void listCategoriesWithAdminAuthValidationTest() throws Exception {
		Category cat=new Category();
		cat.setId(1l);
		when(productService.categories()).thenReturn(Arrays.asList(cat));
		
		this.mockMvc.perform(get("/product/categories")		
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(jsonPath("$.length()").value(1));
	}
	
	@Test
	void listCategoriesByProductWithAdminAuthValidationTest() throws Exception {
		Category cat=new Category();
		cat.setId(1l);
		
		when(productService.findProductCategories(1l)).thenReturn(Arrays.asList(cat));
		
		this.mockMvc.perform(get("/product/1/categories")		
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(jsonPath("$.length()").value(1));
	}	
	
	@Test
	void deleteProductWithAdminAuthPositiveTest() throws Exception {
		
		this.mockMvc.perform(delete("/product/1")
		.with(httpBasic(ADMIN_USERNSME, ADMIN_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isOk())
		.andExpect(content().string(containsString("Deleted Successfully")));
	}	
	
	@Test
	void deleteProductWithUserAuthNegetiveTest() throws Exception {
		
		this.mockMvc.perform(delete("/product/1")
		.with(httpBasic(USER_USERNSME, USER_PASSWORD)))
		.andDo(print()).andExpect(status()
		.isForbidden())
		.andExpect(content().string(containsString("No Access")));
	}	
}
