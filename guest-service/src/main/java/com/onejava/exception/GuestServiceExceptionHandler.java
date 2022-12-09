package com.onejava.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@ControllerAdvice
public class GuestServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GuestNotFoundException.class)
    public final ResponseEntity<ErrorDetailResponse> handleGuestNotFoundException(GuestNotFoundException ex, WebRequest request) {
        ErrorDetailResponse errorResponse = new ErrorDetailResponse(ex.getMessage(),
                ErrorCode.GUEST_NOT_FOUND, OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetailResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetailResponse errorResponse = new ErrorDetailResponse(ex.getMessage()
                ,ErrorCode.INTERNAL_SERVICE_ERROR, OffsetDateTime.now().truncatedTo( ChronoUnit.SECONDS ));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Required for PathVariable validation error
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetailResponse> constraintViolationException(ConstraintViolationException ex) throws IOException {
        ErrorDetailResponse errorResponse = new ErrorDetailResponse(ex.getMessage()
                ,ErrorCode.INVALID_PARAMETER, OffsetDateTime.now().truncatedTo( ChronoUnit.SECONDS ));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
