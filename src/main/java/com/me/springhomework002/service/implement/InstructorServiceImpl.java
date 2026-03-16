package com.me.springhomework002.service.implement;

import com.me.springhomework002.exception.ResourceNotFoundException;
import com.me.springhomework002.model.Request.InstructorRequest;
import com.me.springhomework002.model.entity.Instructor;
import com.me.springhomework002.repository.InstructorRepository;
import com.me.springhomework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {

        Integer offset = (page - 1) * size;

        return instructorRepository.getAllInstructors(offset, size);
    }

    @Override
    public Instructor getInstructorById(Long id) {

        Instructor instructor = instructorRepository.getInstructorById(id);

        if (instructor == null) {
            throw new ResourceNotFoundException("No instructors found with the given ID");
        }

        return instructor;
    }

    @Override
    public Instructor saveInstructor(InstructorRequest request) {
        return instructorRepository.saveInstructor(request);
    }

    @Override
    public Instructor updateInstructor(Long id, InstructorRequest request) {
        return instructorRepository.updateInstructorById(id, request);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteInstructorById(id);
    }
}
