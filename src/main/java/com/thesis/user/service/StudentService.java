package com.thesis.user.service;

import com.thesis.user.dao.StudentMapper;
import com.thesis.user.dto.AddStuInfoDTO;
import com.thesis.user.entity.Student;
import com.thesis.user.vo.StudentDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserService userService;

    // 添加学生信息 FOR 管理员
    public void addStudent(AddStuInfoDTO addStuInfoDTO) {
        if (studentMapper.existsById(addStuInfoDTO.getStudentId())) {
            throw new RuntimeException("添加失败，学生已存在");
        }
        studentMapper.insertStudent(addStuInfoDTO);
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


    /*// 添加学生基础信息
    public ApiResponse<Void> addStudentUser(AddStudentDTO addStudentDTO) {
        if (addStudentDTO == null) {
            return ApiResponse.error(404, "学生信息不能为空", null);
        }
        User studentUser = new User();
        studentUser.setUsername(addStudentDTO.getName());
        studentUser.setRole("学生");
        try {
            userService.addUser(studentUser);
        } catch (Exception e) {
            return ApiResponse.error(404, "用户基础信息创建失败，请查看学生基础信息是否有误", null);
        }
        return ApiResponse.success(null);
    }

    // 使用学生基础信息添加新的学生
    public ApiResponse<Integer> addNewStudentByUser(AddStudentDTO addStudentDTO) {
        // 获取创建成功的学生ID
        int studentId = userService.getUserByUsername(addStudentDTO.getName()).getUserId();
        Student student = new Student();
        student.setStudentId(studentId);
        student.setGrade(addStudentDTO.getGrade());
        student.setClassName(addStudentDTO.getClassName());
        try {
            addStudent(student);
        } catch (Exception e) {
            return ApiResponse.error(404, "学生创建失败，ByStudentService", null);
        }
        return ApiResponse.success(studentId);
    }*/

}
