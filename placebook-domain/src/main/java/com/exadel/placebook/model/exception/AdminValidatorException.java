package com.exadel.placebook.model.exception;



public class AdminValidatorException extends RuntimeException{
    public AdminValidatorException(String message){
        super(message);
    }
    public AdminValidatorException(String message, Throwable cause){
        super(message,cause);
    }
}
