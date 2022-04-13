package com.ecom.services.order;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.access.prepost.PostFilter;

import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.CreatePayment;
import com.ecom.dto.order.OrderStatusDto;
import com.ecom.dto.order.response.OrderPaymentResponseDto;
import com.ecom.dto.order.response.OrderResponseDto;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.status.PaymentStatus;

public interface OrderService {

	public OrderResponseDto createOrder(CreateOrder order);
	
	public OrderResponseDto findOrderById(Long orderId) throws ResourceNotFoundException;
	
	public OrderResponseDto updateOrder(Long orderId,CreateOrder order) throws ResourceNotFoundException;

	public void deleteOrder(Long orderId) throws ResourceNotFoundException;

	public OrderStatusDto orderStatus(@NotNull Long id) throws ResourceNotFoundException;

	public void updateOrderStatus(Long id,OrderStatusDto orderStatus);

	@PostFilter("hasRole('ADMIN') or filterObject.getUserId() == authentication.principal.id()")
	public List<OrderResponseDto> findAll();

	public OrderPaymentResponseDto createPayment(@NotNull Long id, @Valid CreatePayment payment)throws ResourceNotFoundException;
	
	public OrderPaymentResponseDto updateOrderPaymentStatus(Long id,PaymentStatus status);
}
