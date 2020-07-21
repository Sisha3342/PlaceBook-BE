package com.exadel.placebook.model.exception;



public class AdminValidationException extends RuntimeException{
    public AdminValidationException(String message){
        super(message);
    }
    public AdminValidationException(String message, Throwable cause){
        super(message,cause);
    }
}
