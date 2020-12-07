package com.example.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ApiException apiException = new ApiException(
            e.getMessage(),
            e,
            httpStatus,
            ZonedDateTime.now(ZoneId.of("Z")));
    logger.error(e.getMessage(), e.getCause());

    return new ResponseEntity<>(apiException, httpStatus);
    }

}
