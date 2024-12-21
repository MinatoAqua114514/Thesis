package com.thesis.review.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.review.entity.ReviewerReview;
import com.thesis.review.service.ReviewerReviewService;
import com.thesis.review.vo.ReviewerReviewDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviewer")
public class ReviewerReviewController {

    @Autowired
    private ReviewerReviewService reviewerReviewService;

    /**
     * 添加评阅教师审阅信息
     *
     * @param reviewerReview 审阅信息对象，包含审阅的详细内容
     * @return 审阅信息添加结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @PostMapping
    public ApiResponse<Void> addReviewerReview(@RequestBody ReviewerReview reviewerReview) {
        try {
            reviewerReviewService.addReviewerReview(reviewerReview);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除评阅教师审阅信息
     *
     * @param reviewId 需要删除的审阅信息ID
     * @return 审阅信息删除结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Void> deleteReviewerReview(@PathVariable Integer reviewId) {
        try {
            reviewerReviewService.deleteReviewerReview(reviewId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新评阅教师审阅信息
     *
     * @param reviewerReview 需要更新的审阅信息对象，包含审阅的详细内容
     * @return 审阅信息更新结果，成功则返回状态码200及成功信息，失败则返回错误状态码及异常信息
     */
    @PutMapping
    public ApiResponse<Void> updateReviewerReview(@RequestBody ReviewerReview reviewerReview) {
        try {
            reviewerReviewService.updateReviewerReview(reviewerReview);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 查询评阅教师审阅信息
     *
     * @param reviewerId 需要查询的评阅教师ID
     * @return 评阅教师审阅信息查询结果，成功则返回状态码200及查询到的审阅信息，失败则返回错误状态码及异常信息
     */
    @GetMapping("/{reviewerId}")
    public ApiResponse<ReviewerReviewDetailsVo> getReviewerReview(@PathVariable Integer reviewerId) {
        try {
            return ApiResponse.success(reviewerReviewService.findReviewerReview(reviewerId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }
}