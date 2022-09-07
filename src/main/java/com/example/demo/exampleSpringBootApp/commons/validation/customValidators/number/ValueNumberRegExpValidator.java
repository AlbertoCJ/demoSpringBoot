package com.example.demo.exampleSpringBootApp.commons.validation.customValidators.number;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueNumberRegExpValidator implements ConstraintValidator<ValueNumberRegExp, Object> {
    private String regexp;

    @Override
    public void initialize(ValueNumberRegExp annotation) {
    	regexp = annotation.regexp();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || value.equals("")) {
            return true;
        }

        return Pattern.matches(regexp, value.toString());
    }
}
