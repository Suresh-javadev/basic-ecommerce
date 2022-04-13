package com.ecom.controller.callback;

import org.springframework.http.ResponseEntity;

import com.ecom.dto.ErrorDto;
import com.ecom.status.PaymentStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest End Point For Callback Api Related Operations
 * <p> For payment call back from gateway
 * @author suresh 
 * @since 1.0
 * @version 1.0
 */

@Api(value = "CallBack")
public interface CallBackApi {

	@ApiResponses(
			value = {
				@ApiResponse(code = 200,message = "Suceess",response = String.class)
				,@ApiResponse(code = 400,message = "Validation Error",response = ErrorDto.class)
				,@ApiResponse(code = 404,message = "Order Not Found",response = ErrorDto.class)
			})
	@ApiOperation(value = "Payment callback end point| webhook")
	public ResponseEntity<String> paymentCallback(Long orderId,PaymentStatus status );
}
