package com.example.denomination.controller;

import com.example.denomination.exceptions.DefaultRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefaultRestException.class)
    public ResponseEntity<Map<String, Object>> handleException(DefaultRestException e) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", e.getStatus());
        errorResponse.put("success", e.isSuccess());
        errorResponse.put("error", e.getError());
        errorResponse.put("errorTag", e.getErrorTag());
        errorResponse.put("errorDetails", e.getErrorDetails());
        errorResponse.put("validationError", e.isValidationError());

        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(e.getStatus()));
    }
}
