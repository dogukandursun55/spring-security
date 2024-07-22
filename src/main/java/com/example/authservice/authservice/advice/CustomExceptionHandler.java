package com.example.authservice.authservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.authservice.authservice.advice.exception.CustomException;
import com.example.authservice.authservice.advice.exception.FieldAlreadyExistsException;
import com.example.authservice.authservice.advice.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(FieldAlreadyExistsException.class)
    public ResponseEntity<String> HandleException(FieldAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    } 

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> HandleException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> HandleException(BadCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
