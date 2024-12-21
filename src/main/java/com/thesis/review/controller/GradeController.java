package com.thesis.review.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.review.service.GradeService;
import com.thesis.review.vo.StudentGradesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 更新学生成绩
     *
     * @param studentGradesVo 学生成绩信息对象
     * @return 更新结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @PutMapping
    public ApiResponse<Void> updateStudentGrades(@RequestBody StudentGradesVo studentGradesVo) {
        try {
            gradeService.updateStudentGrades(studentGradesVo);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 获取所有学生成绩
     *
     * @return 所有学生成绩信息，成功则返回状态码200及成绩信息，失败则返回错误状态码及异常信息
     */
    @GetMapping("/all")
    public ApiResponse<StudentGradesVo> getAllStudentGrades() {
        try {
            return ApiResponse.success(gradeService.findAllStudentGrades());
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 根据学生ID查询成绩
     *
     * @param studentId 学生ID
     * @return 学生成绩信息，成功则返回状态码200及成绩信息，失败则返回错误状态码及异常信息
     */
    @GetMapping("/{studentId}")
    public ApiResponse<StudentGradesVo> getStudentGradesById(@PathVariable Integer studentId) {
        try {
            return ApiResponse.success(gradeService.findStudentGradesById(studentId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }
}