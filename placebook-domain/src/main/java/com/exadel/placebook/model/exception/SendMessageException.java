package com.exadel.placebook.model.exception;

public class SendMessageException extends RuntimeException {
    public SendMessageException(String message, Exception e){
        super(message);
    }
}
