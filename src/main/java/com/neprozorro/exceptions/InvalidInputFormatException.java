package com.neprozorro.exceptions;

import java.math.BigDecimal;

public class InvalidInputFormatException extends RuntimeException{

    public InvalidInputFormatException(){
        super("Invalid input format. You have to paste two values. Paste min and max total price");
    }

    public InvalidInputFormatException(BigDecimal minValue, BigDecimal maxValue){
         super("Invalid input format. Second value: " + maxValue + " less then first value: " + minValue);
    }
}
