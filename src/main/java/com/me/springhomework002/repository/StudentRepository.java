package com.me.springhomework002.repository;

import com.me.springhomework002.model.Request.StudentRequest;
import com.me.springhomework002.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {


    @Results(
            id = "studentMapper", value = {
                    @Result(property = "studentId", column = "student_id"),
                    @Result(property = "studentName", column = "student_name"),
                    @Result(property = "phoneNumber", column = "phone_number"),
                    @Result(property = "courses", column = "student_id", many = @Many(
                            select = "com.me.springhomework002.repository.StudentCourseRepository.getAllCoursesByStudentId"
                    ) )
    }
    )

    @Select("""
        SELECT * FROM students
        OFFSET #{offset} LIMIT #{size}
    """)
    List<Student> getAllStudents(int offset, Integer size);

//    @ResultMap("studentMapper")
    @Select("""
        INSERT INTO students VALUES (default, #{studentName}, #{email}, #{phoneNumber}) RETURNING student_id
    """)
    Long saveStudent(StudentRequest request);

    @ResultMap("studentMapper")
    @Select("""
        SELECT * FROM students
        WHERE student_id = #{id}
    """)
    Student getStudentById(Long id);


    @ResultMap("studentMapper")
    @Select("""
        UPDATE students
        SET student_name = #{req.studentName}, email = #{req.email}, phone_number = #{req.phoneNumber}
        WHERE student_id = #{id}
        RETURNING *
    """)
    Student updateStudentById(Long id, @Param("req") StudentRequest request);

    @Delete("""
        DELETE FROM students WHERE student_id = #{id}
    """)
    void deleteStudentById(Long id);
}
