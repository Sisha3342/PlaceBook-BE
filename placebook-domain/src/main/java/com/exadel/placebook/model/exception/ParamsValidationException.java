package com.exadel.placebook.model.exception;

public class ParamsValidationException extends RuntimeException{
    public ParamsValidationException(String message){
        super(message);
    }
    public ParamsValidationException(String message, Throwable cause){
        super(message,cause);
    }
}
