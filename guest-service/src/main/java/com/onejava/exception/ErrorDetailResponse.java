package com.onejava.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class ErrorDetailResponse {
    private String errorMessage;
    private ErrorCode errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)  // Only properties with non-null values are to be included
    private OffsetDateTime timestamp;
}
