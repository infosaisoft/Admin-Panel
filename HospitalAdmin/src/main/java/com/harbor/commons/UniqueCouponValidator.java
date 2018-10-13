package com.harbor.commons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class UniqueCouponValidator implements ConstraintValidator<UniqueCoupon, String> {

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void initialize(UniqueCoupon constraint) {
		// TODO Auto-generated method stub
		
	}


}
