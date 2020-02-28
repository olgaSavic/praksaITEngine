package com.ftn.projekat.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameTitleValidator implements ConstraintValidator<NameTitleConstraint, String>{
	
	@Override
    public void initialize(NameTitleConstraint name) {
    }

	@Override
	public boolean isValid(String field, ConstraintValidatorContext context) {
		if (field == null || field.length() == 0)
		{
			return true ;
		}
		else {
		return field != null && field.matches("[A-Za-z]+$")
				&& (field.length() > 1) && (field.length() < 50);
		}
	}
}
