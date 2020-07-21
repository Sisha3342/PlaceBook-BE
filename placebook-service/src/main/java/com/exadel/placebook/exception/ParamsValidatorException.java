package com.exadel.placebook.exception;

public class ParamsValidatorException extends RuntimeException{
    public ParamsValidatorException(String message){
        super(message);
    }
    public ParamsValidatorException(String message, Throwable cause){
        super(message,cause);
    }
}
