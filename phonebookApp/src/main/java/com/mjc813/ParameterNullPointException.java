package com.mjc813;

public class ParameterNullPointException extends NullPointerException {
//    public ParameterNullPointException() {}
    public ParameterNullPointException(String message) {
        super(message);
    }
}