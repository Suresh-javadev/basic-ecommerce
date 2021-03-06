package com.ecom.dto.order;

import java.math.BigDecimal;

import com.ecom.status.PaymentOption;
import com.ecom.status.PaymentStatus;

public class CreatePayment {

	private BigDecimal amount;

	private PaymentStatus status;

	private PaymentOption option;

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
