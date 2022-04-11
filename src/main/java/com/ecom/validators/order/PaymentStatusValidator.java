package com.ecom.validators.order;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ecom.status.PaymentStatus;

public class PaymentStatusValidator implements ConstraintValidator<ValidPaymentStatus,PaymentStatus>{
	
	private PaymentStatus[] subset;
	
	@Override
	public void initialize(ValidPaymentStatus constraintAnnotation) {		
		this.subset= constraintAnnotation.anyOf();
	}
	
	@Override
	public boolean isValid(PaymentStatus value, ConstraintValidatorContext context) {
		return value == null || Arrays.asList(subset).contains(value);
	}

}
