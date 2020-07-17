package com.exadel.placebook.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private final String message;
    private final HttpStatus httpStatus;

    public ErrorResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
