package com.ecom.controller.order;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.ErrorDto;
import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.CreatePayment;
import com.ecom.dto.order.OrderStatusDto;
import com.ecom.dto.order.response.OrderPaymentResponseDto;
import com.ecom.dto.order.response.OrderResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Order")
public interface OrderApi {

	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Suceess",response = OrderResponseDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create Order")
	public ResponseEntity<OrderResponseDto> create(@Valid CreateOrder order);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 200,message = "Suceess",response = OrderResponseDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Get A Order By Order Id")
	public ResponseEntity<OrderResponseDto> order(@NotNull Long id);

	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Suceess",response = OrderResponseDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Update Order By Order Id")
	public ResponseEntity<OrderResponseDto> update(@NotNull Long orderId,@Valid CreateOrder order);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 200,message = "Deleted Successfully",response = String.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Delete A Order By Order Id")
	public ResponseEntity<String> orderDelete(@NotNull Long id);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 200,message = "Suceess",response = OrderStatusDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Get A Order Status By Order Id")
	public ResponseEntity<OrderStatusDto> orderStatus(@NotNull Long id);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 200,message = "Suceess",response = OrderResponseDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Get All Orders")
	public ResponseEntity<List<OrderResponseDto>> orders();
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 200,message = "Suceess",response = OrderStatusDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Update A Order Status By Order Id")
	public ResponseEntity<OrderStatusDto> orderStatusUpdate(@NotNull Long id,OrderStatusDto orderStatus);
	
	@ApiResponses(
			value = {
				@ApiResponse(code = 201,message = "Suceess",response = OrderPaymentResponseDto.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Create Payment For Order")
	public ResponseEntity<OrderPaymentResponseDto> orderPayment(@NotNull Long id,CreatePayment payment);	
}
