package com.example.demo.exampleSpringBootApp.commons.validation.customValidators.number;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueNumberRegExpValidator.class)
public @interface ValueNumberRegExp {
	String regexp();
    String message() default "must be match regexp";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
