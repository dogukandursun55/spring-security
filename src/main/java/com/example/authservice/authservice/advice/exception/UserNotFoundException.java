package com.example.authservice.authservice.advice.exception;
import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class UserNotFoundException extends CustomException{

    public UserNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
