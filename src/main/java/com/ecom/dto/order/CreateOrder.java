package com.ecom.dto.order;

import java.math.BigDecimal;
import java.util.Collection;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static com.ecom.constants.Constants.*;

@ApiModel(description = "Modal for create order")
public class CreateOrder {

	@ApiModelProperty(required = true,dataType = "Long", example = "1")
	@NotNull(message = "{order.userid.notnull}")
	@Range(min=1, message = "{order.userid.range}")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateOrder other = (CreateOrder) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
}
