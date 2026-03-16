package com.me.springhomework002.controller;

import com.me.springhomework002.exception.ResourceNotFoundException;
import com.me.springhomework002.model.Request.InstructorRequest;
import com.me.springhomework002.model.Response.ApiResponse;
import com.me.springhomework002.model.entity.Instructor;
import com.me.springhomework002.service.InstructorService;
import com.me.springhomework002.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Operation(summary = "Get all instructors")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        List<Instructor> instructors = instructorService.getAllInstructors(page, size);

        return ResponseUtil.success(HttpStatus.OK, "Instructors fetched successfully", instructors);
    }

    @Operation(summary = "Get instructor by ID")
    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Long id) {

        Instructor instructor = instructorService.getInstructorById(id);

        if (instructor == null) {
            throw new ResourceNotFoundException("No instructors found with the given ID");
        }

        return ResponseUtil.success(HttpStatus.OK, "Instructor fetched successfully", instructor);

    }

    @Operation(summary = "Create a new instructor")
    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorRequest request) {

        Instructor instructor = instructorService.saveInstructor(request);

        return ResponseUtil.success(HttpStatus.CREATED, "Instructor created successfully", instructor);
    }

    @Operation(summary = "Update instructor by ID")
    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateStudentById(@PathVariable("instructor-id") Long id, @RequestBody InstructorRequest request) {

        Instructor instructor = instructorService.getInstructorById(id);

        if (instructor == null) {
            throw new ResourceNotFoundException("No instructors found with the given ID");
        }

        Instructor updatedInstructor = instructorService.updateInstructor(id, request);

        return ResponseUtil.success(HttpStatus.OK, "Instructor updated successfully", updatedInstructor);
    }

    @Operation(summary = "Delete instructor by ID")
    @DeleteMapping("{instructor-id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructorById(@PathVariable("instructor-id") Long id) {

        Instructor instructor = instructorService.getInstructorById(id);

        if (instructor == null) {
            throw new ResourceNotFoundException("No instructors found with the given ID");
        }

        instructorService.deleteInstructorById(id);

        return ResponseUtil.success(HttpStatus.OK, "Instructor deleted successfully");
    }


}

