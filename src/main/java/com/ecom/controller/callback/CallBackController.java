package com.ecom.controller.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.services.order.OrderService;
import com.ecom.status.PaymentStatus;

@RestController
public class CallBackController implements CallBackApi {
	@Autowired
	private OrderService orderService;
	
	@Override
	@GetMapping("/callback/payment/{orderId}/{status}")
	public ResponseEntity<String> paymentCallback(@PathVariable(required = true, value = "orderId") Long orderId,
			@PathVariable(required = true,value="status") PaymentStatus status) {
		orderService.updateOrderPaymentStatus(orderId, status);
		return ResponseEntity.ok("Success");
	}

}
