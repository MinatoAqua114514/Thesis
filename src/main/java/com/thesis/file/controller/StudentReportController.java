package com.thesis.file.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.file.dto.FileStatusDTO;
import com.thesis.file.dto.openingDTO;
import com.thesis.file.dto.topicDTO;
import com.thesis.file.entity.MiddleReport;
import com.thesis.file.entity.OpeningReport;
import com.thesis.file.entity.Thesis;
import com.thesis.file.entity.TopicSubmission;
import com.thesis.file.service.StudentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentReport")
public class StudentReportController {

    @Autowired
    private StudentReportService studentReportService;

    /**
     * 提交选题报告信息。
     *
     * @param topicSubmission 选题报告主题提交对象，包含学生提交的选题报告详情。
     * @return 返回操作结果，成功时提供成功信息，失败时返回错误代码及描述。
     */
    @PostMapping("/submission")
    public ApiResponse<Void> submitReport(@RequestBody TopicSubmission topicSubmission) {
        try {
            studentReportService.addTopicSubmission(topicSubmission);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除选题报告提交。
     *
     * @param submissionId 选题报告提交ID，用于标识要删除的选题报告记录。
     * @return 返回操作结果。成功时返回成功信息，失败时返回错误代码404及异常信息。
     */
    @DeleteMapping("/submission")
    public ApiResponse<Void> deleteSubmission(int submissionId) {
        try {
            studentReportService.deleteTopicSubmission(submissionId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新选题报告提交。
     *
     * @param topicSubmission 选题报告提交对象，包含学生提交的选题报告详情。
     * @return 返回操作结果。成功时返回成功信息，失败时返回错误代码404及异常信息。
     */
    @PutMapping("/submission")
    public ApiResponse<Void> updateSubmission(TopicSubmission topicSubmission) {
        try {
            studentReportService.updateTopicSubmission(topicSubmission);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 获取选题报告提交信息。
     *
     * @param topicId 选题报告提交ID，用于标识要查询的选题报告记录。
     * @return 返回操作结果。成功时返回选题报告对象，失败时返回错误代码404及异常信息。
     */
    @GetMapping("/submission")
    public ApiResponse<TopicSubmission> getSubmission(int topicId) {
        try {
            studentReportService.findTopicSubmission(topicId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(studentReportService.findTopicSubmission(topicId));
    }

    /**
     * 获取学生的选题报告提交信息。
     *
     * @param studentId 学生ID，用于查询指定学生提交的信息
     * @return ApiResponse<TopicSubmission> 包含查询结果的响应对象。如果查询成功，返回200状态码和TopicSubmission对象；如果查询失败，返回404状态码和错误信息
     */
    @GetMapping("/submission/student")
    public ApiResponse<TopicSubmission> getSubmissionByStudentId(int studentId) {
        try {
            return ApiResponse.success(studentReportService.findTopicSubmissionByStudentId(studentId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 专业负责人审阅选题报告。
     *
     * @param topicSubmission 选题报告提交对象，包含需要更新的专业负责人意见信息。
     * @return 如果操作成功，则返回成功响应，否则返回错误代码404及异常信息。
     */
    @PutMapping("/submission/reviewer")
    public ApiResponse<Void> updateReviewer(TopicSubmission topicSubmission) {
        try {
            studentReportService.updateReviewOpinion(topicSubmission);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 院领导审阅选题报告。
     *
     * @param topicSubmission 选题报告提交对象，包含需要更新的院领导意见信息。
     * @return 如果操作成功，则返回成功响应，否则返回错误代码404及异常信息。
     */
    @PutMapping("/submission/leader")
    public ApiResponse<Void> updateLeader(TopicSubmission topicSubmission) {
        try {
            studentReportService.updateLeaderOpinion(topicSubmission);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }


    /**
     * 审批用选题报告视图信息
     *
     * @return ApiResponse<List < topicDTO>> 返回话题视图数据的响应，成功时包含数据列表，失败时包含错误信息
     */
    @GetMapping("/topicView")
    public ApiResponse<List<topicDTO>> getTopicView() {
        try {
            studentReportService.getTopicView();
        } catch (Exception e) {
            return ApiResponse.error(404, "getTopicView失败", null);
        }
        return ApiResponse.success(studentReportService.getTopicView());
    }






    /**
     * 添加开题报告
     *
     * @param openingReport 开题报告对象，包含学生开题报告的相关信息
     * @return ApiResponse<Void> 如果成功添加，则返回带有成功状态的ApiResponse，否则返回错误状态的ApiResponse，其中包含错误码和错误信息
     */
    @PostMapping("/opening")
    public ApiResponse<Void> addOpeningReport(@RequestBody OpeningReport openingReport) {
        try {
            studentReportService.addOpeningReport(openingReport);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除开题报告
     *
     * @param reportId 需要删除的开题报告ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/opening")
    public ApiResponse<Void> deleteOpeningReport(int reportId) {
        try {
            studentReportService.deleteOpeningReport(reportId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新开题报告
     *
     * @param openingReport 需要更新的开题报告对象，包含开题报告的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/opening")
    public ApiResponse<Void> updateOpeningReport(OpeningReport openingReport) {
        try {
            studentReportService.updateOpeningReport(openingReport);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * ID查询单个开题报告详细信息
     *
     * @param reportId 需要查询的开题报告ID
     * @return ApiResponse<OpeningReport> 包含查询结果的响应对象。若成功，data字段携带OpeningReport对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/opening")
    public ApiResponse<OpeningReport> getOpeningReport(int reportId) {
        try {
            return ApiResponse.success(studentReportService.findOpeningReport(reportId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 获取学生开题报告详细信息
     *
     * @param studentId 需要查询的学生ID
     * @return ApiResponse<OpeningReport> 包含查询结果的响应对象。若成功，data字段携带OpeningReport对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/opening/student")
    public ApiResponse<OpeningReport> getOpeningReportByStudentId(int studentId) {
        try {
            return ApiResponse.success(studentReportService.findOpeningReportByStudentId(studentId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 专业负责人对开题报告进行审核给出意见
     *
     * @param openingReport 开题报告对象，包含专业负责人的审核意见
     * @return ApiResponse<Void> 如果成功更新，则返回带有成功状态的ApiResponse，否则返回错误状态的ApiResponse，其中包含错误码和错误信息
     */
    @PutMapping("/opening/reviewer")
    public ApiResponse<Void> updateOpeningReviewer(OpeningReport openingReport) {
        try {
            studentReportService.updateOpeningReviewOpinion(openingReport);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 审批用开题视图数据
     *
     * @return ApiResponse<List < OpeningDTO>> 返回开场视图数据的响应，成功时包含数据列表，失败时包含错误信息
     */
    @GetMapping("/openingView")
    public ApiResponse<List<openingDTO>> getOpeningView() {
        try {
            studentReportService.getOpeningView();
        } catch (Exception e) {
            return ApiResponse.error(404, "getOpeningView失败", null);
        }
        return ApiResponse.success(studentReportService.getOpeningView());
    }






    /**
     * 添加中期报告
     *
     * @param middleReport 中期报告对象，包含学生中期报告的相关信息
     * @return ApiResponse<Void> 如果成功添加，则返回带有成功状态的ApiResponse，否则返回错误状态的ApiResponse，其中包含错误码和错误信息
     */
    @PostMapping("/middle")
    public ApiResponse<Void> addMiddleReport(@RequestBody MiddleReport middleReport) {
        try {
            studentReportService.addMiddleReport(middleReport);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除中期报告
     *
     * @param reportId 需要删除的中期报告ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/middle")
    public ApiResponse<Void> deleteMiddleReport(int reportId) {
        try {
            studentReportService.deleteMiddleReport(reportId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新中期报告
     *
     * @param middleReport 需要更新的中期报告对象，包含中期报告的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/middle")
    public ApiResponse<Void> updateMiddleReport(MiddleReport middleReport) {
        try {
            studentReportService.updateMiddleReport(middleReport);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * ID查询单个中期报告详细信息
     *
     * @param reportId 需要查询的中期报告ID
     * @return ApiResponse<MiddleReport> 包含查询结果的响应对象。若成功，data字段携带MiddleReport对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/middle")
    public ApiResponse<MiddleReport> getMiddleReport(int reportId) {
        try {
            return ApiResponse.success(studentReportService.findMiddleReport(reportId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 获取学生中期报告详细信息
     *
     * @param studentId 需要查询的学生ID
     * @return ApiResponse<MiddleReport> 包含查询结果的响应对象。若成功，data字段携带MiddleReport对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/middle/student")
    public ApiResponse<MiddleReport> getMiddleReportByStudentId(int studentId) {
        try {
            return ApiResponse.success(studentReportService.findMiddleReportByStudentId(studentId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }






    /**
     * 添加论文
     *
     * @param thesis 论文对象，包含学生论文的相关信息
     * @return ApiResponse<Void> 如果成功添加，则返回带有成功状态的ApiResponse，否则返回错误状态的ApiResponse，其中包含错误码和错误信息
     */
    @PostMapping("/thesis")
    public ApiResponse<Void> addThesis(@RequestBody Thesis thesis) {
        try {
            studentReportService.addThesis(thesis);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除论文
     *
     * @param thesisId 需要删除的论文ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/thesis")
    public ApiResponse<Void> deleteThesis(int thesisId) {
        try {
            studentReportService.deleteThesis(thesisId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新论文
     *
     * @param thesis 需要更新的论文对象，包含论文的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/thesis")
    public ApiResponse<Void> updateThesis(Thesis thesis) {
        try {
            studentReportService.updateThesis(thesis);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * ID查询单个论文详细信息
     *
     * @param thesisId 需要查询的论文ID
     * @return ApiResponse<Thesis> 包含查询结果的响应对象。若成功，data字段携带Thesis对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/thesis")
    public ApiResponse<Thesis> getThesis(int thesisId) {
        try {
            return ApiResponse.success(studentReportService.findThesis(thesisId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 获取学生论文详细信息
     *
     * @param studentId 需要查询的学生ID
     * @return ApiResponse<Thesis> 包含查询结果的响应对象。若成功，data字段携带Thesis对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/thesis/student")
    public ApiResponse<Thesis> getThesisByStudentId(int studentId) {
        try {
            return ApiResponse.success(studentReportService.findThesisByStudentId(studentId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }






    /**
     * 指导老师ID获取学生报告状态和成绩
     *
     * @param advisorId 指导老师ID
     * @return ApiResponse<List<FileStatusDTO>> 包含查询结果的响应对象。若成功，data字段携带FileStatusDTO对象列表；若失败，code和message字段携带错误信息
     */
    @GetMapping("/status/advisor")
    public ApiResponse<List<FileStatusDTO>> getStudentReportStatusByAdvisor(int advisorId) {
        try {
            return ApiResponse.success(studentReportService.findStudentReportStatusByAdvisor(advisorId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 获取所有学生报告状态和成绩
     *
     *
     * @return ApiResponse<List<FileStatusDTO>> 包含查询结果的响应对象。若成功，data字段携带FileStatusDTO对象列表；若失败，code和message字段携带错误信息
     */
    @GetMapping("/status/all")
    public ApiResponse<List<FileStatusDTO>> getAllStudentReportStatus() {
        try {
            return ApiResponse.success(studentReportService.findAllStudentReportStatus());
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }
}
