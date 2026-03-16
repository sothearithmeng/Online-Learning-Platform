package com.me.springhomework002.service.implement;

import com.me.springhomework002.exception.ResourceNotFoundException;
import com.me.springhomework002.model.Request.StudentRequest;
import com.me.springhomework002.model.entity.Student;
import com.me.springhomework002.repository.StudentCourseRepository;
import com.me.springhomework002.repository.StudentRepository;
import com.me.springhomework002.service.CourseService;
import com.me.springhomework002.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {

    private String name;

    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final CourseService courseService;

    public StudentServiceImplement(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.courseService = courseService;
    }


    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {

        int offset = (page - 1) * size;

        return studentRepository.getAllStudents(offset, size);
    }

    @Override
    public Student saveStudent(StudentRequest request) {

        for (Long id : request.courseId()) {
            courseService.getCourseById(id);
        }

        Student student = studentRepository.saveStudent(request);
        for (Long courseId : request.courseId()) {
            studentCourseRepository.insertStudentIdAndCourseId(student.getStudentId(), courseId);
        }

        return student;
    }

    @Override
    public Student getStudentById(Long id) {

        Student student = studentRepository.getStudentById(id);

        if (student == null) {
            throw new ResourceNotFoundException("No students found with the given ID");
        }

        return student;
    }

    @Override
    public Student updateStudentById(Long id, StudentRequest request) {

        getStudentById(id);

        for (Long courseId : request.courseId()) {
            courseService.getCourseById(courseId);
        }

        studentCourseRepository.removeCoursesByStudentId(id);

        List<Long> newCourses = request.courseId();

        for (Long newCourse : newCourses) {
            studentCourseRepository.insertStudentIdAndCourseId(id, newCourse);
        }

        return studentRepository.updateStudentById(id, request);

    }

    @Override
    public void deleteStudentById(Long id) {

        getStudentById(id);

        studentRepository.deleteStudentById(id);
    }
}
