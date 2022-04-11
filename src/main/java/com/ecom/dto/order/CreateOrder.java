package com.ecom.dto.order;

import java.math.BigDecimal;
import java.util.Collection;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static com.ecom.constants.Constants.*;

@ApiModel(description = "Modal for create order")
public class CreateOrder {

	@ApiModelProperty(required = true,dataType = "Long", example = "1")
	@NotNull(message = "{order.userid.notnull}")
	@Size(min=1,message = "{order.userid.size}")
	private Long userId;
	
	@ApiModelProperty(required = true,dataType = "BigDecimal", example = "1000.25")
	@Digits(integer = 8,fraction = 2, message = "{order.amount.digits}")
	private BigDecimal amount;
	
	@ApiModelProperty(required = true,dataType = "String", example = "sk@gmail.com")
	@Email(regexp = EMIAL_REGEX,message = "{order.email.email}")
	private String email;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "{order.orderdetails.notnull}")
	private Collection<CreateOrderDetails> orderDetails;

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

	public Collection<CreateOrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<CreateOrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}
