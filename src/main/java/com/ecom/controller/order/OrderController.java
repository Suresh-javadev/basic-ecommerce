package com.ecom.controller.order;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.CreatePayment;
import com.ecom.dto.order.OrderStatusDto;
import com.ecom.dto.order.response.OrderPaymentResponseDto;
import com.ecom.dto.order.response.OrderResponseDto;

@RestController
public class OrderController implements OrderApi{

	@Override
	@PreAuthorize("@customMethodSecurity.validUserIdAccess(authentication, #order.getUserId())")
	@PostMapping("/order")
	public ResponseEntity<OrderResponseDto> create(@RequestBody @Valid CreateOrder order) {
		return null;
	}

	@Override
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderResponseDto> order(@PathVariable("orderId") @NotNull Long id) {
		return null;
	}

	@Override
	@PreAuthorize("@customMethodSecurity.validUserIdAccess(authentication, #order.getUserId())")
	@PutMapping("/order/{orderId}")
	public ResponseEntity<OrderResponseDto> update(@PathVariable("orderId") @NotNull Long orderId,@RequestBody @Valid CreateOrder order) {
		return null;
	}	
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<String> orderDelete(@PathVariable("orderId") @NotNull Long id) {
		return null;
	}
	
	@Override
	@GetMapping("/order/{orderId}/status")
	public ResponseEntity<OrderStatusDto> orderStatus(@PathVariable("orderId") @NotNull Long id) {
		return null;
	}
	
	@Override
	@PutMapping("/order/{orderId}/status")
	public ResponseEntity<OrderStatusDto> orderStatusUpdate(@PathVariable("orderId") @NotNull Long id,@RequestBody @Valid OrderStatusDto orderStatus) {
		return null;
	}

	@Override
	@PreAuthorize("#payment.getStatus().name() == 'PENDING'")
	@PostMapping("/order/{orderId}/payment")
	public ResponseEntity<OrderPaymentResponseDto> orderPayment(@PathVariable("orderId") @NotNull Long id,@RequestBody @Valid CreatePayment payment) {
		
		return null;
	}

}
