package com.me.springhomework002.repository;

import com.me.springhomework002.model.Request.CourseRequest;
import com.me.springhomework002.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Results( id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "com.me.springhomework002.repository.InstructorRepository.getInstructorById")),
    })
    @Select("""
        SELECT * FROM courses OFFSET (#{page}-1) * #{size} LIMIT #{size}
    """)
    List<Course> getAllCourses(Integer page, Integer size);

    @ResultMap("courseMapper")
    @Select("""
        SELECT * FROM courses
        WHERE course_id = #{id}
    """)
    Course getCourseById(Long id);

    @ResultMap("courseMapper")
    @Select("""
        INSERT INTO courses VALUES (default, #{courseName}, #{description}, #{instructorId}) RETURNING *
    """)
    Course saveCourse(CourseRequest request);

    @ResultMap("courseMapper")
    @Select("""
        UPDATE courses SET course_name = #{req.courseName}, description = #{req.description}, instructor_id = #{req.instructorId}
        WHERE course_id = #{id} RETURNING *
    """)
    Course updateCourseById(Long id, @Param("req") CourseRequest request);

    @Delete("""
        DELETE FROM courses WHERE course_id = #{id}
    """)
    void deleteCourseById(Long id);
}
