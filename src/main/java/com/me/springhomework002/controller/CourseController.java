package com.me.springhomework002.controller;


import com.me.springhomework002.exception.ResourceNotFoundException;
import com.me.springhomework002.model.Request.CourseRequest;
import com.me.springhomework002.model.Response.ApiResponse;
import com.me.springhomework002.model.entity.Course;
import com.me.springhomework002.service.CourseService;
import com.me.springhomework002.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAlCourses(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        List<Course> courses = courseService.getAllCourses(page, size);

            return ResponseUtil.success(HttpStatus.OK, "Courses fetched successfully", courses);
    }

    @Operation(summary = "Get course by ID")
    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Long id) {

        Course course = courseService.getCourseById(id);

        return ResponseUtil.success(HttpStatus.OK, "Course fetched successfully", course);
    }

    @Operation(summary = "Create a new course")
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseRequest request) {

        Course course = courseService.saveCourse(request);

        return ResponseUtil.success(HttpStatus.CREATED, "Course created successfully", course);
    }

    @Operation(summary = "Update course by ID")
    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable("course-id") Long id, @RequestBody CourseRequest request) {

        Course updatedCourse = courseService.updateCourseById(id, request);

        return ResponseUtil.success(HttpStatus.OK, "Course updated successfully", updatedCourse);

    }

    @Operation(summary = "Delete course by ID")
    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable("course-id") Long id) {

        courseService.getCourseById(id);

        courseService.deleteCourseById(id);

        return ResponseUtil.success(HttpStatus.OK, "Course deleted successfully");
    }
 }

