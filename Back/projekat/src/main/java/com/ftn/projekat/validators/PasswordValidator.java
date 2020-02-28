package com.ftn.projekat.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String>{
	@Override
    public void initialize(PasswordConstraint password) {
    }

	@Override
	public boolean isValid(String field, ConstraintValidatorContext context) {
		return field != null && (field.length() < 30);
	}
}
