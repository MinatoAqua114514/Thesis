package com.thesis.review.service;

import com.thesis.review.dao.ReviewerReviewMapper;
import com.thesis.review.entity.ReviewerReview;
import com.thesis.review.vo.ReviewerReviewDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewerReviewService {

    @Autowired
    private ReviewerReviewMapper reviewerReviewMapper;

    // 评阅教师提交审阅表 FOR 评阅教师
    public void addReviewerReview(ReviewerReview reviewerReview) {
        if (reviewerReview == null) {
            throw new RuntimeException("reviewerReview不能为空");
        }
        reviewerReviewMapper.insertReviewerReview(reviewerReview);
    }

    // 删除评阅教师审阅表 FOR 评阅教师
    public void deleteReviewerReview(Integer reviewId) {
        if (reviewId == null) {
            throw new RuntimeException("reviewId不能为空");
        }

        if (reviewerReviewMapper.selectReviewerReview(reviewId) == null) {
            throw new RuntimeException("该审阅表不存在");
        }
        reviewerReviewMapper.deleteReviewerReview(reviewId);
    }

    // 更新评阅教师审阅表 FOR 评阅教师
    public void updateReviewerReview(ReviewerReview reviewerReview) {
        if (reviewerReview.getReviewId() == null) {
            throw new RuntimeException("reviewId不能为空");
        }
        if (reviewerReviewMapper.selectReviewerReview(reviewerReview.getReviewId()) == null) {
            throw new RuntimeException("该审阅表不存在");
        }
        if (reviewerReviewMapper.selectReviewerReview(reviewerReview.getReviewId()).equals(reviewerReview)) {
            throw new RuntimeException("更新内容重复");
        }
        reviewerReviewMapper.updateReviewerReview(reviewerReview);
    }

    // ID查询单个评阅教师审阅表 FOR 管理员、评阅教师
    public ReviewerReviewDetailsVo findReviewerReview(Integer reviewerId) {
        if (reviewerId == null) {
            throw new RuntimeException("reviewerId不能为空");
        }
        ReviewerReviewDetailsVo reviewerReview = reviewerReviewMapper.selectReviewerReview(reviewerId);
        if (reviewerReview == null) {
            throw new RuntimeException("该审阅表不存在");
        }
        return reviewerReview;
    }
}
