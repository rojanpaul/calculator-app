package com.conciso.calculator.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = OperandsValidatorImpl.class)
@Documented
public @interface OperandsValidator {
	String message() default "One (or more) of the Operand(s) is(are) zero(s) in Request. Please remove zeros and try again";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
