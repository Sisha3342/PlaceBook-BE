package com.exadel.placebook.exception;

import com.exadel.placebook.model.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.exadel.placebook.model.exception.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {ApplicationRuntimeException.class})
    public ResponseEntity<?>  handleRestException(ApplicationRuntimeException e){
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<?>  handleRestException(ValidationException e){
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value={ParamsValidationException.class})
    public ResponseEntity<?>  handleRestException(ParamsValidationException e){
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value={AdminValidationException.class})
    public ResponseEntity<?>  handleRestException(AdminValidationException e){
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<?>  handleRestException(Exception e){
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =new ErrorResponse(
                e.getMessage(),
                badRequest
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = BookingException.class)
    public ResponseEntity<?> handleRestException(BookingException e) {
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = SecurityValidationException.class)
    public ResponseEntity<?> handleRestException(SecurityValidationException e) {
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.FORBIDDEN;
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handleRestException(EntityNotFoundException e) {
        logger.error(e.getMessage(),e);
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }

}
