package com.ecom.validators.order;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ecom.status.PaymentOption;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentOptionValidator.class)
public @interface ValidPaymentOption {
	
	PaymentOption[] anyOf();

	String message() default "must be any of {CREDIT_CARD, DEBIT_CARD, NET_BANKING, CASH}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
