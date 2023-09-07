package com.neprozorro.validation.annotation;

import com.neprozorro.validation.validator.TotalPriceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = TotalPriceValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTotalPrice{

    String regex() default "^\\s*(\\d+(\\.\\d+)?)\\s*,\\s*(\\d+(\\.\\d+)?)\\s*";

    String message() default "Invalid writing in field: \"total Price\"";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
