package com.onejava.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String defaultMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorDetailResponse errorResponse = new ErrorDetailResponse(defaultMessage
                ,ErrorCode.BAD_REQUEST, OffsetDateTime.now().truncatedTo( ChronoUnit.SECONDS ));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
