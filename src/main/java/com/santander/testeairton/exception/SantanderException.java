package com.santander.testeairton.exception;

public class SantanderException extends RuntimeException {

    public SantanderException(String msg, Throwable t) {
        super(msg, t);
    }

    public SantanderException(String msg) {
        super(msg, null);
    }
}
