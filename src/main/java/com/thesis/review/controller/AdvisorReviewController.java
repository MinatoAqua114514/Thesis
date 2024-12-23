package com.thesis.review.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.review.entity.AdvisorReview;
import com.thesis.review.service.AdvisorReviewService;
import com.thesis.review.vo.AdvisorReviewDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisorReview")
public class AdvisorReviewController {

    @Autowired
    private AdvisorReviewService advisorReviewService;

    /**
     * 添加指导老师审阅信息
     *
     * @param advisorReview 审阅信息对象，包含审阅的详细内容
     * @return 审阅信息添加结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @PostMapping
    public ApiResponse<Void> addAdvisorReview(@RequestBody AdvisorReview advisorReview) {
        try {
            advisorReviewService.addAdvisorReview(advisorReview);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除指导老师审阅信息
     *
     * @param reviewId 需要删除的审阅信息ID
     * @return 审阅信息删除结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Void> deleteAdvisorReview(@PathVariable Integer reviewId) {
        try {
            advisorReviewService.deleteAdvisorReview(reviewId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新指导老师审阅信息
     *
     * @param advisorReview 需要更新的审阅信息对象，包含审阅的详细内容
     * @return 审阅信息更新结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @PutMapping
    public ApiResponse<Void> updateAdvisorReview(@RequestBody AdvisorReview advisorReview) {
        try {
            advisorReviewService.updateAdvisorReview(advisorReview);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 查询指导老师审阅信息
     *
     * @param advisorId 需要查询的指导老师ID
     * @return 指导老师审阅信息查询结果，成功则返回状态码200及查询到的审阅信息，失败则返回错误状态码及异常信息
     */
    @GetMapping("/{advisorId}")
    public ApiResponse<List<AdvisorReviewDetailsVo>> getAdvisorReview(@PathVariable Integer advisorId) {
        try {
            return ApiResponse.success(advisorReviewService.findAdvisorReview(advisorId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }
}