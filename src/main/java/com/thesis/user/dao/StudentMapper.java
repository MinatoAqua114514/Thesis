package com.thesis.user.dao;

import com.thesis.user.entity.Student;
import com.thesis.user.vo.StudentDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    /*学生信息管理*/
    void insertStudent(Student student);

    void deleteStudent(@Param("studentId") String studentId);

    void updateStudent(Student student);

    StudentDetailsVo selectStudentDetails(@Param("studentId") String studentId);
}
