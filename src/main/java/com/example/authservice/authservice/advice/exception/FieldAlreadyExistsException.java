package com.example.authservice.authservice.advice.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class FieldAlreadyExistsException extends RuntimeException{
    private HttpStatus status;
    private String message;
    private String fieldName;

    public FieldAlreadyExistsException(String fieldName) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = fieldName + " already exists.";
        this.fieldName = fieldName;
    }
}
