package com.ecom.dto.order.response;

import java.math.BigDecimal;

import com.ecom.status.PaymentOption;
import com.ecom.status.PaymentStatus;

public class OrderPaymentResponseDto {

	private Long id;

	private Long orderId;

	private BigDecimal amount;

	private PaymentStatus status;

	private PaymentOption option;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public PaymentOption getOption() {
		return option;
	}

	public void setOption(PaymentOption option) {
		this.option = option;
	}

}
