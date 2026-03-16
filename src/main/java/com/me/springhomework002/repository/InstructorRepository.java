package com.me.springhomework002.repository;

import com.me.springhomework002.model.Request.InstructorRequest;
import com.me.springhomework002.model.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Results(
            id = "instructorMapper", value = {
                    @Result(property = "instructorId", column = "instructor_id"),
                    @Result(property = "instructorName", column = "instructor_name")
            }
    )
    @Select("""
        SELECT * FROM instructors OFFSET #{offset} LIMIT #{size}
    """)
    List<Instructor> getAllInstructors(Integer offset, Integer size);

    @ResultMap("instructorMapper")
    @Select("""
        SELECT * FROM instructors
        WHERE  instructor_id = #{id}
    """)
    Instructor getInstructorById(Long id);

    @ResultMap("instructorMapper")
    @Select("""
        INSERT INTO instructors VALUES (default, #{instructorName}, #{email}) RETURNING *
    """)
    Instructor saveInstructor(InstructorRequest request);

    @ResultMap("instructorMapper")
    @Select("""
        UPDATE instructors SET instructor_name = #{req.instructorName}, email = #{req.email}
        WHERE instructor_id = #{id}
        RETURNING *
    """)
    Instructor updateInstructorById(Long id, @Param("req") InstructorRequest request);

    @Delete("""
        DELETE FROM instructors WHERE instructor_id = #{id}
    """)
    void deleteInstructorById(Long id);


}
