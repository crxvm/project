package com.example.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import java.util.UUID;

@ControllerAdvice
public class ApiExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
    HttpStatus httpStatus;

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        if(MethodArgumentNotValidException.class.isInstance(e)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            ErrorResponse errorResponse = new ErrorResponse(
                    "required fields are not filled");

            logger.error(e.getMessage(), e);

            return new ResponseEntity<>(errorResponse, httpStatus);
        }
        else if(NoResultException.class.isInstance(e)) {
            httpStatus = HttpStatus.NOT_FOUND;
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(errorResponse, httpStatus);
        }
        else  {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            UUID uuid = UUID.randomUUID();
            ErrorResponse errorResponse = new ErrorResponse("Internal server error. UUID:" + uuid.toString());
            logger.error(e.getMessage() + "UUID: " + uuid.toString(), e);
            return new ResponseEntity<>(errorResponse, httpStatus);
        }

    }

}
