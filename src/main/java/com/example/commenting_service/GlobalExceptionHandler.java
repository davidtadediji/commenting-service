package com.example.commenting_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // You can customize the response here

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, String> errorMessages = new HashMap<>();

        for(FieldError fieldError: fieldErrors){
            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());

        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);

    }

}
