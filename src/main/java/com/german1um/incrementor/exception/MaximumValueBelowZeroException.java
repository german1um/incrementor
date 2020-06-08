package com.german1um.incrementor.exception;

public class MaximumValueBelowZeroException extends RuntimeException{
    public MaximumValueBelowZeroException() {
        super("Value is below zero.");
    }
}
