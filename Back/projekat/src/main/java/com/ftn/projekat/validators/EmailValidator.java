package com.ftn.projekat.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstaint, String> {
	
	@Override
    public void initialize(EmailConstaint email) {
    }

	@Override
	public boolean isValid(String field, ConstraintValidatorContext context) {
		if (field == null || field.length() == 0) {
			return true ;
		}
		else {
			return field != null && field.matches("^[a-zA-Z0-9.!#$%&\\'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*.com$")
				&& (field.length() > 8) && (field.length() < 30);
		}
	}

}
