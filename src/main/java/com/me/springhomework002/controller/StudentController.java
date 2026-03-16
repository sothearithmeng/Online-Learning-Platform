package com.me.springhomework002.controller;

import com.me.springhomework002.model.Request.StudentRequest;
import com.me.springhomework002.model.Response.ApiResponse;
import com.me.springhomework002.model.entity.Student;
import com.me.springhomework002.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        List<Student> result = studentService.getAllStudents(page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        HttpStatus.OK.value(),
                        "Students retrieved successfully",
                        result,
                        Instant.now()
                )
        );
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest request) {

        Student student = studentService.saveStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        HttpStatus.CREATED.value(),
                        "Student created successfully",
                        student,
                        Instant.now()
                )
        );
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Long id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    HttpStatus.NOT_FOUND.value(),
                                    "No students found with the given ID",
                                    null,
                                    Instant.now()
                            )
                    );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        HttpStatus.OK.value(),
                        "Student fetched successfully",
                        student,
                        Instant.now()
                )
        );
    }

    @Operation(summary = "Update student by ID")
    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable("student-id") Long id, @RequestBody StudentRequest request) {

        Student student = studentService.updateStudentById(id, request);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              new ApiResponse<>(
                      false,
                      HttpStatus.NOT_FOUND.value(),
                      "No students found with the given ID",
                      null,
                      Instant.now()
              )
            );
        }

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Student updated successfully",
                student,
                Instant.now()
        ));
    }

    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<?>> deleteStudentById(@PathVariable("student-id") Long id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            HttpStatus.NOT_FOUND.value(),
                            "No students found with the given ID",
                            null,
                            Instant.now()
                    )
            );
        }
        studentService.deleteStudentById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        HttpStatus.OK.value(),
                        "Student deleted successfully",
                        null,
                        Instant.now()
                )
        );
    }

}
