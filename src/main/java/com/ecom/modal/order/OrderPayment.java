package com.ecom.modal.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ecom.modal.ModalTimeStamp;
import com.ecom.status.PaymentOption;
import com.ecom.status.PaymentStatus;

@Entity
@Table(name = "tbl_order_payment")
public class OrderPayment extends ModalTimeStamp{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id",nullable = false)
	private Order order;
	
	@Column(name="amount",nullable = false,precision = 10,scale = 2)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable = false)
	private PaymentStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="option",nullable = false)
	private PaymentOption option;

	public OrderPayment() {}
	
	public OrderPayment(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
