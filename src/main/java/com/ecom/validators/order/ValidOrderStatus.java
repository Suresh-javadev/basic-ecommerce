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

import com.ecom.status.OrderStatus;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrderStatusValidator.class)
public @interface ValidOrderStatus {

	OrderStatus[] anyOf();

	String message() default "must be any of {CREATED, PROCESSED, CANCELLED, DELIVERED, REJECTED}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
