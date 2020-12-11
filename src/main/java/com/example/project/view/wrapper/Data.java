package com.example.project.view.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Класс для обертки в data
 * @see com.example.project.controller.JSONResponseWrapper
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public class Data<T> {
    private T data;

    public Data(T data) {
        this.data = data;
    }

    public Data() {
    }

    public T getData() {
        return data;
    }
}
