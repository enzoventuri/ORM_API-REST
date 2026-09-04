package com.example.demo.exceptions;

import com.example.demo.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException exception, HttpServletRequest http
    ) {
        ErrorResponse error = ErrorResponse.create(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Arguments",
                exception.getMessage(),
                http.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodInvalidException(
            IllegalArgumentException exception, HttpServletRequest http
    ) {
        ErrorResponse error = ErrorResponse.create(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Method",
                exception.getMessage(),
                http.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception exception, HttpServletRequest http
    ) {
        ErrorResponse error = ErrorResponse.create(
                HttpStatus.BAD_REQUEST.value(),
                "Error on the server side",
                exception.getMessage(),
                http.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
