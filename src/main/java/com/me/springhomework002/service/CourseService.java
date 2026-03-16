package com.me.springhomework002.service;

import com.me.springhomework002.model.Request.CourseRequest;
import com.me.springhomework002.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer page, Integer size);

    Course getCourseById(Long id);

    Course saveCourse(CourseRequest request);

    Course updateCourseById(Long id, CourseRequest request);

    void deleteCourseById(Long id);
}
