package com.ecom.services.order;


import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.response.OrderResponseDto;
import com.ecom.exception.ResourceNotFoundException;

public interface OrderService {

	public OrderResponseDto createOrder(CreateOrder order);
	
	public OrderResponseDto findOrderById(Long orderId) throws ResourceNotFoundException;
	
	public OrderResponseDto updateOrder(Long orderId,CreateOrder order) throws ResourceNotFoundException;

	public void deleteOrder(Long orderId) throws ResourceNotFoundException;
}
