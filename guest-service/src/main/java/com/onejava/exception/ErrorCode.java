package com.onejava.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST("400", HttpStatus.BAD_REQUEST),
    GUEST_NOT_FOUND("404", HttpStatus.NOT_FOUND),
    INTERNAL_SERVICE_ERROR("500", HttpStatus.INTERNAL_SERVER_ERROR);

    @JsonValue
    public final String code;
    public final HttpStatus associatedStatus;
}
