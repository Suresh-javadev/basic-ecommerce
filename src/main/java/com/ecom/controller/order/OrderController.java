package com.ecom.controller.order;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
import com.ecom.services.order.OrderService;

/**
 * Rest End Point For Order Api Related Operations
 * <p> Provide Order create, fetch, delete, edit end points.
 * @author suresh
 * @since 1.0
 * @version 1.0
 */

@RestController
public class OrderController implements OrderApi{
	
	@Autowired
	private OrderService orderService;
	
	@Override
	@PreAuthorize("@customMethodSecurity.validUserIdAccess(authentication, #order.getUserId())")
	@PostMapping("/order")
	public ResponseEntity<OrderResponseDto> create(@RequestBody @Valid CreateOrder order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostAuthorize("@customMethodSecurity.validOrderIdAccess(authentication, returnObject.getBody())")
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderResponseDto> order(@PathVariable("orderId") @NotNull Long id) {
		return ResponseEntity.ok(orderService.findOrderById(id));
	}

	@Override
	@PreAuthorize("@customMethodSecurity.validUserIdAccess(authentication, #order.getUserId())")
	@PutMapping("/order/{orderId}")
	public ResponseEntity<OrderResponseDto> update(@PathVariable("orderId") @NotNull Long orderId,@RequestBody @Valid CreateOrder order) {
		return ResponseEntity.ok(orderService.updateOrder(orderId, order));
	}	
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<String> orderDelete(@PathVariable("orderId") @NotNull Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.ok("Deleted Successfully");
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN') or @customMethodSecurity.validOrderIdAccess(authentication, #id)")
	@GetMapping("/order/{orderId}/status")
	public ResponseEntity<OrderStatusDto> orderStatus(@PathVariable("orderId") @NotNull Long id) {
		OrderStatusDto status = orderService.orderStatus(id);
		return ResponseEntity.ok(status);
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN') or (#orderStatus.getOrderStatus().name() == 'CANCELLED' and @customMethodSecurity.validOrderIdAccess(authentication, #id))")
	@PutMapping("/order/{orderId}/status")
	public ResponseEntity<OrderStatusDto> orderStatusUpdate(@PathVariable("orderId") @NotNull Long id,@RequestBody @Valid OrderStatusDto orderStatus) {
		orderService.updateOrderStatus(id,orderStatus);
		return ResponseEntity.ok(orderStatus);
	}

	@Override
	@PreAuthorize("@customMethodSecurity.validOrderIdAccess(authentication, #id) and #payment.getStatus().name() == 'PENDING'")
	@PostMapping("/order/{orderId}/payment")
	public ResponseEntity<OrderPaymentResponseDto> orderPayment(@PathVariable("orderId") @NotNull Long id,@RequestBody @Valid CreatePayment payment) {
		OrderPaymentResponseDto paymentDetails = orderService.createPayment(id,payment);
		return ResponseEntity.ok(paymentDetails);
	}

	@Override
	//service level @PostFilter("hasRole('ADMIN') or filterObject.getUserId() == authentication.principal.id()")
	@GetMapping("/orders")
	public ResponseEntity<List<OrderResponseDto>> orders() {
		List<OrderResponseDto> list = orderService.findAll();	
		return ResponseEntity.ok(list);
	}

}
