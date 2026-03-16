package com.me.springhomework002.model.Request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record StudentRequest(String studentName, @Schema(example = "example@gmail.com") String email, String phoneNumber, List<Long> courseId) {
}
