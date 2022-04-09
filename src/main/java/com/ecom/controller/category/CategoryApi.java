package com.ecom.controller.category;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.ErrorDto;
import com.ecom.dto.category.CreateCategory;
import com.ecom.modal.product.Category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Category")
public interface CategoryApi {
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Category Created",response = Category.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create Product")
	public ResponseEntity<Category> create(CreateCategory cat);
	
	@ApiOperation(value = "Get category by id", response = Category.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<Category> category(@ApiParam(required = true,example = "1",type = "Long",name = "categoryId") Long catId);
	
	@ApiOperation(value = "Get list of category", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<List<Category>> category();
	
	@ApiOperation(value = "Delete category by id", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Deleted Suceessfully"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public ResponseEntity<String> delete(@ApiParam(required = true,example = "1",type = "Long",name = "categoryId") Long catId);
}
