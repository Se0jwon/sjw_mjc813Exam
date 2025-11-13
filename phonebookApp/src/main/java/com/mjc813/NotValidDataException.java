package com.mjc813;

public class NotValidDataException extends RuntimeException {
//    public NotValidDataException() {}
    public NotValidDataException(String message) {
        super(message);
    }
}
