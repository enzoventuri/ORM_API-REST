package com.example.demo.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
    Integer status,
    String error,
    String message,
    String path,
    LocalDateTime timestamp
) {
    public static ErrorResponse create(Integer status,
                                String error,
                                String message,
                                String path,
                                LocalDateTime timestamp) {
        return new ErrorResponse(
            status, error, message, path, timestamp);
    }
}
