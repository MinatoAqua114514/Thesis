package com.thesis.review.service;

import com.thesis.review.dao.GradeMapper;
import com.thesis.review.vo.StudentGradesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    // 修改学生成绩 FOR 管理员
    public void updateStudentGrades(StudentGradesVo studentGradesVo) {
        if (studentGradesVo == null) {
            throw new RuntimeException("学生成绩信息不能为空");
        }
        // 学生观不存在
        if (gradeMapper.selectStudentGradesById(studentGradesVo.getStudentId()) == null) {
            throw new RuntimeException("学生成绩信息不存在");
        }
        gradeMapper.updateStudentGrades(studentGradesVo);
    }

    // 获取所有学生成绩 FOR 管理员、专业负责人、院领导、评阅教师
    public List<StudentGradesVo> findAllStudentGrades() {
        return gradeMapper.selectAllStudentGrades();
    }

    // ID查询单个学生成绩 FOR All
    public StudentGradesVo findStudentGradesById(Integer studentId) {
        return gradeMapper.selectStudentGradesById(studentId);
    }

    // 指导老师ID获取指导学生成绩
    public List<StudentGradesVo> findStudentGradesByAdvisorId(Integer advisorId) {
        return gradeMapper.selectStudentGradesByAdvisorId(advisorId);
    }
}
