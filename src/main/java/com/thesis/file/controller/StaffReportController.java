package com.thesis.file.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.file.entity.TaskBook;
import com.thesis.file.service.StaffReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskBook")
public class StaffReportController {

    @Autowired
    private StaffReportService staffReportService;

    /**
     * 添加任务书
     *
     * @param taskBook 需要添加的任务书对象，包含任务书的所有信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping
    public ApiResponse<Void> addTaskBook(@RequestBody TaskBook taskBook) {
        try {
            staffReportService.addTaskBook(taskBook);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除任务书
     *
     * @param taskBookId 需要删除的任务书ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping
    public ApiResponse<Void> deleteTaskBook(int taskBookId) {
        try {
            staffReportService.deleteTaskBook(taskBookId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新任务书
     *
     * @param taskBook 需要更新的任务书对象，包含任务书的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping
    public ApiResponse<Void> updateTaskBook(TaskBook taskBook) {
        try {
            staffReportService.updateTaskBook(taskBook);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 根据任务书ID获取任务书信息
     *
     * @param taskBookId 任务书ID，用于查询特定任务书的详细信息
     * @return ApiResponse<TaskBook> 包含查询结果的响应对象。若成功，data字段携带TaskBook对象；若失败，code和message字段携带错误信息
     */
    @GetMapping
    public ApiResponse<TaskBook> findTaskBookById(Integer taskBookId) {
        try {
            staffReportService.findTaskBook(taskBookId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(staffReportService.findTaskBook(taskBookId));
    }

    /**
     * 专业负责人审查任务书
     *
     * @param taskBook 需要更新的任务书对象，包含任务书的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/reviewer")
    public ApiResponse<Void> updateTaskBookReviewer(TaskBook taskBook) {
        try {
            staffReportService.updateReviewOpinion(taskBook);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 获取所有任务书信息
     *
     * @return ApiResponse<List<TaskBook>> 包含查询结果的响应对象。若成功，data字段携带TaskBook对象列表；若失败，code和message字段携带错误信息
     */
    @GetMapping("/reviewer/getAllTaskBook")
    public ApiResponse<List<TaskBook>> getAllTaskBook() {
        try {
            staffReportService.findAllTaskBook();
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(staffReportService.findAllTaskBook());
    }

    /**
     * 院领导审查任务书
     *
     * @param taskBook 需要更新的任务书对象，包含任务书的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/leader")
    public ApiResponse<Void> updateTaskBookLeader(TaskBook taskBook) {
        try {
            staffReportService.updateLeaderOpinion(taskBook);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 根据指导老师ID获取任务书
     *
     * @param advisorId 指导老师ID，用于查询特定指导老师的任务书
     * @return ApiResponse<List<TaskBook>> 包含查询结果的响应对象。若成功，data字段携带TaskBook对象列表；若失败，code和message字段携带错误信息
     */
    @GetMapping("/advisor/getTaskBook")
    public ApiResponse<List<TaskBook>> getTaskBookByAdvisorId(Integer advisorId) {
        try {
            staffReportService.findTaskBookByAdvisorId(advisorId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(staffReportService.findTaskBookByAdvisorId(advisorId));
    }
}
