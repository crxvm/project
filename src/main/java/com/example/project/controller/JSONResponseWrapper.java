package com.example.project.controller;

import com.example.project.exception.ErrorResponse;
import com.example.project.view.wrapper.Data;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseBodyAdvise для обертки ответа в data
 */
@ControllerAdvice
public class JSONResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     *Перехватывает json и оборачивает его в data
     * @param body ответ в формате json
     * @return объект типа {@link Data}
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ErrorResponse) {
            return body;
        }
        return new Data<Object>(body);
    }

}
