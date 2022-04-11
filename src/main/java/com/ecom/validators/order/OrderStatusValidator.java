package com.ecom.validators.order;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ecom.status.OrderStatus;

public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, OrderStatus>{
	
	private OrderStatus[] subset;
	
	@Override
	public void initialize(ValidOrderStatus constraintAnnotation) {		
		this.subset = constraintAnnotation.anyOf();
	}
	
	@Override
	public boolean isValid(OrderStatus value, ConstraintValidatorContext context) {		
		return value == null || Arrays.asList(subset).contains(value);
	}
}
