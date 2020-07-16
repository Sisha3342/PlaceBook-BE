package com.exadel.placebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ErrorResponseException.class})
    public ResponseEntity<Object>  handleRestException(ErrorResponseException e){
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object>  handleRestException(Exception e){
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
}
