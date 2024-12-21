package com.thesis.user.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.user.entity.Student;
import com.thesis.user.service.StudentService;
import com.thesis.user.vo.StudentDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 添加学生信息
     *
     * @param student 需要添加的学生对象，包含学生的所有信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping("/add")
    public ApiResponse<Void> addStudent(Student student) {
        try {
            studentService.addStudent(student);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除学生信息
     *
     * @param studentId 需要删除的学生ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteStudent(int studentId) {
        try {
            studentService.deleteStudent(studentId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新学生信息
     *
     * @param student 需要更新的学生对象，包含学生的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/update")
    public ApiResponse<Void> updateStudent(Student student) {
        try {
            studentService.updateStudent(student);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * ID查询学生详细信息
     *
     * @param studentId 需要查询的学生ID
     * @return ApiResponse<StudentDetailsVo> 查询操作的响应结果。成功时，返回状态码200及StudentDetailsVo对象；失败时，返回状态码404及错误信息
     */
    @GetMapping("/details")
    public ApiResponse<StudentDetailsVo> findStudentDetailsById(int studentId) {
        try {
            return ApiResponse.success(studentService.findStudentDetailsById(studentId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 指导老师获取指导学生列表
     *
     * @param advisorId 指导老师ID
     * @return ApiResponse<List<StudentDetailsVo>> 查询操作的响应结果。成功时，返回状态码200及StudentDetailsVo对象列表；失败时，返回状态码404及错误信息
     */
    @GetMapping("/advisor")
    public ApiResponse<List<StudentDetailsVo>> findStudentDetailsByAdvisorId(int advisorId) {
        try {
            List<StudentDetailsVo> studentDetailsVoList = studentService.findStudentDetailsByAdvisorId(advisorId);
            return ApiResponse.success(studentDetailsVoList);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 查询所有学生详细信息
     *
     * @return ApiResponse<List<StudentDetailsVo>> 查询操作的响应结果。成功时，返回状态码200及StudentDetailsVo对象列表；失败时，返回状态码404及错误信息
     */
    @GetMapping("/allDetails")
    public ApiResponse<List<StudentDetailsVo>> findAllStudentDetails() {
        try {
            return ApiResponse.success(studentService.findAllStudentDetails());
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }
}
