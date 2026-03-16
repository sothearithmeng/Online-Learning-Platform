package com.me.springhomework002.service;

import com.me.springhomework002.model.Request.StudentRequest;
import com.me.springhomework002.model.Response.ApiResponse;
import com.me.springhomework002.model.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {


    List<Student> getAllStudents(Integer page, Integer size);

    Student saveStudent(StudentRequest request);

    Student getStudentById(Long id);

    Student updateStudentById(Long id, StudentRequest request);

    void deleteStudentById(Long id);
}
