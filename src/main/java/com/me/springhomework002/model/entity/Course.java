package com.me.springhomework002.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//public record Course(Long courseId, String courseName, String description, Instructor instructor) {
//
//}


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}