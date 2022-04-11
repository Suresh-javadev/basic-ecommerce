package com.ecom.dto.order;

import java.math.BigDecimal;
import java.util.Collection;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

import static com.ecom.constants.Constants.*;

@ApiModel(description = "Modal for create order")
public class CreateOrder {

	@NotNull(message = "{order.userid.notnull}")
	@Size(min=1,message = "{order.userid.size}")
	private Long userId;
	
	@Digits(integer = 8,fraction = 2, message = "{order.amount.digits}")
	private BigDecimal amount;
	
	@Email(regexp = EMIAL_REGEX,message = "{order.email.email}")
	private String email;
	
	@NotNull(message = "{order.orderdetails.notnull}")
	private Collection<CreateOrderDetails> orderDetails;
	
	
}
