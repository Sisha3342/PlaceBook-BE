package com.exadel.placebook.model.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
    public ValidationException(String message, Throwable cause){
        super(message,cause);
    }
}
