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

/**
 * Перехватчик исключений для контроллеров
 */
@ControllerAdvice
public class ApiExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * Обрабатывает исключения:
     * {@link MethodArgumentNotValidException}, {@link NoResultException}, {@link Exception}
     * @param e исключение
     * @return объект обертка с сообщением об ошибке
     */

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        if(e instanceof MethodArgumentNotValidException) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "required fields are not filled");

            logger.error(e.getMessage(), e);

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        else if(e instanceof NoResultException) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        else  {
            UUID uuid = UUID.randomUUID();
            ErrorResponse errorResponse = new ErrorResponse("Internal server error. UUID:" + uuid.toString());
            logger.error(e.getMessage() + "UUID: " + uuid.toString(), e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
