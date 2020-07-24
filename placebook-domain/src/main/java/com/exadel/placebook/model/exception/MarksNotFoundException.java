package com.exadel.placebook.model.exception;

public class MarksNotFoundException extends RuntimeException {
    public MarksNotFoundException(String message){
        super(message);
    }
    public MarksNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
