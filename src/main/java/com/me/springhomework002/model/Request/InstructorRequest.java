package com.me.springhomework002.model.Request;

import io.swagger.v3.oas.annotations.media.Schema;

public record InstructorRequest(String instructorName, @Schema(example = "example@gmail.com") String email) {
}
