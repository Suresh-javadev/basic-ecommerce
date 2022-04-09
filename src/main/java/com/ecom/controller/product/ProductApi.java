package com.ecom.controller.product;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.ErrorDto;
import com.ecom.dto.product.CreateProduct;
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
				@ApiResponse(code = 201,message = "Product Created",response = Product.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create Product")
	public ResponseEntity<Product> create(CreateProduct product);
	
	@ApiOperation(value = "Get product by id", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<Product> product(@ApiParam(required = true,example = "1",type = "Long",name = "productId") Long productId);
	
	@ApiOperation(value = "Get list of products", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<List<Product>> products();
	
	@ApiOperation(value = "Delete product by id", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleted Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<String> delete(@ApiParam(required = true,example = "1",type = "Long",name = "productId") Long productId);
	
}
