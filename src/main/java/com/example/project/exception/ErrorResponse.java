package com.example.project.exception;

/**
 * Отображение при возникновении ошибок
 */
public class ErrorResponse {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}

