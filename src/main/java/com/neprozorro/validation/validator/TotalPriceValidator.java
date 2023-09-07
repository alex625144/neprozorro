package com.neprozorro.validation.validator;

import com.neprozorro.validation.annotation.ValidTotalPrice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TotalPriceValidator implements ConstraintValidator<ValidTotalPrice, String> {

    private static final String RANGE_REGEX = "^\\s*(\\d+(\\.\\d+)?)\\s*,\\s*(\\d+(\\.\\d+)?)\\s*$";
    private static final Pattern RANGE_PATTERN = Pattern.compile(RANGE_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Matcher matcher = RANGE_PATTERN.matcher(value);
        if (matcher.matches()) {
            BigDecimal firstNumber = new BigDecimal(matcher.group(1));
            BigDecimal secondNumber = new BigDecimal(matcher.group(3));

            return firstNumber.compareTo(secondNumber) < 0 || secondNumber.equals(BigDecimal.ZERO);
        }

        return false;
    }

    @Override
    public void initialize(ValidTotalPrice validTotalPrice) {
        try {
           Pattern.compile(validTotalPrice.regex());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }
}
