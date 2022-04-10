package com.ecom.controller.product;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.ErrorDto;
import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.modal.product.Category;
import com.ecom.modal.product.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Product")
public interface ProductApi {

	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Suceess",response = Product.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create Product")
	public ResponseEntity<Product> create(CreateProduct product);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Suceess",response = Product.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Update Product")
	public ResponseEntity<Product> update(Long porductId,CreateProduct product);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Suceess",response = Product.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create Category")
	public ResponseEntity<Category> createCategory(CreateCategory category);
	
	@ApiOperation(value = "Get A Product By id", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<Product> product(@ApiParam(required = true,example = "1",type = "Long",name = "productId") Long productId);
	
	@ApiOperation(value = "Get All Products", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess",response = Product.class, responseContainer = "List"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<List<Product>> products();
	
	@ApiOperation(value = "Delete A Product By id", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleted Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<String> delete(@ApiParam(required = true,example = "1",type = "Long",name = "productId") Long productId);
	
	@ApiOperation(value = "Get All Categories For A Product By Productid", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess",response = Category.class, responseContainer = "List"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<List<Category>> productCategory(@ApiParam(required = true,example = "1",type = "Long",name = "productId") Long productId);
	
	@ApiOperation(value = "Get All Categories", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess",response = Category.class, responseContainer = "List"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<List<Category>> categories();
	
	@ApiOperation(value = "UnMapped A Category To A Product", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Unmapped Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<String> unmapCategory(Long productId,Long categoryId) ;
	
	@ApiOperation(value = "Mapped A Category To A Product", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mapped Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })	
	public ResponseEntity<String> mapCategory(Long productId,Long categoryId) ;
	
	@ApiOperation(value = "Get A Category By Id", response = Category.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<Category> category(Long id);
	
	@ApiOperation(value = "Delete A Category By Id", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleted Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<String> deleteCategory(Long id);
	
}
