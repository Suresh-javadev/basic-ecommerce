package com.ecom.dto.order.response;

import java.math.BigDecimal;
import java.util.Collection;

import com.ecom.status.OrderStatus;

public class OrderResponseDto {

	private Long id;

	private Long userId;

	private BigDecimal amount;

	private String email;

	private OrderStatus status;

	private OrderPaymentResponseDto orderPayment;

	private Collection<OrderDetailsResponseDto> orderDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public OrderPaymentResponseDto getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(OrderPaymentResponseDto orderPayment) {
		this.orderPayment = orderPayment;
	}

	public Collection<OrderDetailsResponseDto> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetailsResponseDto> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
