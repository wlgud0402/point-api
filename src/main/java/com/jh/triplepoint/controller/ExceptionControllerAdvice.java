package com.jh.triplepoint.controller;

import com.jh.triplepoint.model.api.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse errorResponse() {
        return new ErrorResponse();
    }
}
