package com.exadel.placebook.exception;

public class SecurityValidationException extends RuntimeException{
    public SecurityValidationException() {
        super();
    }

    public SecurityValidationException(String message) {
        super(message);
    }
}
