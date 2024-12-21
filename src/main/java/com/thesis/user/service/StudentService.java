package com.thesis.user.service;

import com.thesis.user.dao.StudentMapper;
import com.thesis.user.entity.Student;
import com.thesis.user.vo.StudentDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // 添加学生信息 FOR 管理员
    public void addStudent(Student student) {
        if (studentMapper.existsById(student.getStudentId())) {
            throw new RuntimeException("添加失败，学生已存在");
        }
        studentMapper.insertStudent(student);
    }

    // 删除学生信息 FOR 管理员
    public void deleteStudent(int studentId) {
        if (!studentMapper.existsById(studentId)) {
            throw new RuntimeException("删除失败，学生不存在");
        }
        studentMapper.deleteStudent(studentId);
    }

    // 更新学生信息 FOR 管理员
    public void updateStudent(Student student) {
        if (!studentMapper.existsById(student.getStudentId())) {
            throw new RuntimeException("更新失败，学生不存在");
        }
        if (student.equals(studentMapper.selectStudentById(student.getStudentId()))) {
            throw new RuntimeException("更新失败，学生信息未发生变化");
        }
        studentMapper.updateStudent(student);
    }

    // ID查询学生详细信息 FOR ALL
    public StudentDetailsVo findStudentDetailsById(int studentId) {
        if (!studentMapper.existsById(studentId)) {
            throw new RuntimeException("查找失败，学生不存在");
        }
        return studentMapper.selectStudentDetailsById(studentId);
    }

    // 指导老师获取指导学生列表 FOR 指导老师
    public List<StudentDetailsVo> findStudentDetailsByAdvisorId(int advisorId) {
        return studentMapper.selectStudentDetailsByAdvisorId(advisorId);
    }

    // 查询所有学生详细信息 FOR 管理员、专业负责人、院领导、评阅教师
    public List<StudentDetailsVo> findAllStudentDetails() {
        return studentMapper.selectAllStudentDetails();
    }
}
