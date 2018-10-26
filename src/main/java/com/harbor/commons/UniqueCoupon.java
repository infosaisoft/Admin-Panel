package com.harbor.commons;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCouponValidator.class)
public @interface UniqueCoupon {
	
	String message() default "Coupon name already exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
