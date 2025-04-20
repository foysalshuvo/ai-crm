package com.ai.crm.util;

import com.ai.crm.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseBuilder {
    // ✅ Success Response (Generically typed)
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                message,
                data
        );
        return ResponseEntity.ok(response);
    }

    // ✅ Created Response (Generically typed)
    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                message,
                data
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ Error Response (Generically typed)
    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse<>(
                LocalDateTime.now(),
                status.value(),
                message,
                null
        );
        return new ResponseEntity<>(response, status);
    }
}
