package com.exadel.placebook.exception;

public class ErrorResponseException extends RuntimeException{
    public ErrorResponseException(String message){
        super(message);
    }
    public ErrorResponseException(String message, Throwable cause){
        super(message,cause);
    }
}
