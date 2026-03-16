package com.me.springhomework002.util;

import com.me.springhomework002.model.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> success(
            HttpStatus status,
            String message,
            T payload
    ) {
        return ResponseEntity.status(status)
                .body(ApiResponse.success(status.value(), message, payload));
    }


    public static ResponseEntity<ApiResponse<Void>> success(
            HttpStatus status,
            String message
    ) {
        return ResponseEntity.status(status)
                .body(ApiResponse.success(status.value(), message, null));
    }


    public static ResponseEntity<ApiResponse<Void>> error(
            HttpStatus status,
            String message
    ) {
        return ResponseEntity.status(status)
                .body(ApiResponse.error(status.value(), message));
    }

}