package com.me.springhomework002.service.implement;

import com.me.springhomework002.exception.ResourceNotFoundException;
import com.me.springhomework002.model.Request.CourseRequest;
import com.me.springhomework002.model.entity.Course;
import com.me.springhomework002.repository.CourseRepository;
import com.me.springhomework002.service.CourseService;
import com.me.springhomework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    public CourseServiceImpl(CourseRepository courseRepository, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        return courseRepository.getAllCourses(page, size);
    }

    @Override
    public Course getCourseById(Long id) {

        Course course = courseRepository.getCourseById(id);

        if (course == null) {
            throw new ResourceNotFoundException("No courses found with the given ID");
        }

        return course;
    }

    @Override
    public Course saveCourse(CourseRequest request) {

        instructorService.getInstructorById(request.instructorId());

        return courseRepository.saveCourse(request);
    }

    @Override
    public Course updateCourseById(Long id, CourseRequest request) {

        getCourseById(id);

        instructorService.getInstructorById(request.instructorId());

        return courseRepository.updateCourseById(id, request);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteCourseById(id);
    }
}
