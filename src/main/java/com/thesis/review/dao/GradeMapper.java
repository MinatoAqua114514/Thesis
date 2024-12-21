package com.thesis.review.dao;

import com.thesis.review.vo.StudentGradesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GradeMapper {

    // 修改学生成绩
    void updateStudentGrades(StudentGradesVo studentGradesVo);

    // 获取所有学生成绩
    StudentGradesVo selectAllStudentGrades();

    // ID查询学生成绩
    StudentGradesVo selectStudentGradesById(@Param("studentId") Integer studentId);
}
