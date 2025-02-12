package com.ftn.projekat.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String>{
	@Override
    public void initialize(PasswordConstraint password) {
    }

	@Override
	public boolean isValid(String field, ConstraintValidatorContext context) {
		if (field == null || field.length() == 0)
		{
			return true ;
		}
		else {
			return field != null && (field.length() > 4) && (field.length() < 30);
		}
	}
}
