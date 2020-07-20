package com.exadel.placebook.exception;

import com.exadel.placebook.model.exception.AdminValidatorException;
import com.exadel.placebook.model.exception.ApplicationRuntimeException;
import com.exadel.placebook.model.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ApplicationRuntimeException.class})
    public ResponseEntity<?>  handleRestException(ApplicationRuntimeException e){
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value={AdminValidatorException.class})
    public ResponseEntity<?>  handleRestException(AdminValidatorException e){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<?>  handleRestException(Exception e){
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
}
