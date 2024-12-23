package com.thesis.user.dao;

import com.thesis.user.dto.AddStuInfoDTO;
import com.thesis.user.entity.Student;
import com.thesis.user.vo.StudentDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    /*学生信息管理*/
    void insertStudent(AddStuInfoDTO addStuInfoDTO);

    void deleteStudent(@Param("studentId") int studentId);

    void updateStudent(Student student);

    List<StudentDetailsVo> selectAllStudentDetails();

    StudentDetailsVo selectStudentDetailsById(@Param("studentId") int studentId);

    boolean existsById(@Param("studentId") int studentId);

    Student selectStudentById(@Param("studentId") int studentId);

    List<StudentDetailsVo> selectStudentDetailsByAdvisorId(@Param("advisorId") int advisorId);
}
