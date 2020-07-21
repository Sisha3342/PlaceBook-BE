package com.exadel.placebook.model.exception;

public class ParamsValidatorException extends RuntimeException{
    public ParamsValidatorException(String message){
        super(message);
    }
    public ParamsValidatorException(String message, Throwable cause){
        super(message,cause);
    }
}
