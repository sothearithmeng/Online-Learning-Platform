package com.me.springhomework002.service;

import com.me.springhomework002.model.Request.InstructorRequest;
import com.me.springhomework002.model.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer page, Integer size);

    Instructor getInstructorById(Long id);

    Instructor saveInstructor(InstructorRequest request);

    Instructor updateInstructor(Long id, InstructorRequest request);

    void deleteInstructorById(Long id);
}
