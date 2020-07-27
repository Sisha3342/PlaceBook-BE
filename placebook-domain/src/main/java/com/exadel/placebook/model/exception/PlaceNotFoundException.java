package com.exadel.placebook.model.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(String message){
        super(message);
    }
    public PlaceNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
