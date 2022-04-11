package com.ecom.validators.order;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ecom.status.PaymentOption;

public class PaymentOptionValidator implements ConstraintValidator<ValidPaymentOption,PaymentOption>{

	private PaymentOption[] subset;
	
	@Override
	public void initialize(ValidPaymentOption constraintAnnotation) {
		this.subset=constraintAnnotation.anyOf();
	}
	
	@Override
	public boolean isValid(PaymentOption value, ConstraintValidatorContext context) {
		return value == null || Arrays.asList(subset).contains(value);
	}

}
