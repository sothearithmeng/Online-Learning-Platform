package com.me.springhomework002.model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
//public record ApiResponse<T>(Boolean success, HttpStatus status, String message, T payload, Instant time) {
//}
public record ApiResponse<T>(
        boolean success,
        int status,
        String message,
        T payload,
        Instant timestamp
) {

    public static <T> ApiResponse<T> success(int status, String message, T payload) {
        return new ApiResponse<>(
                true,
                status,
                message,
                payload,
                Instant.now()
        );
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(
                false,
                status,
                message,
                null,
                Instant.now()
        );
    }

}