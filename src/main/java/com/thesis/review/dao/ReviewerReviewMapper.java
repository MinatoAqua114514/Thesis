package com.thesis.review.dao;

import com.thesis.review.entity.ReviewerReview;
import com.thesis.review.vo.ReviewerReviewDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewerReviewMapper {

    // 评阅教师提交审阅表
    void insertReviewerReview(ReviewerReview reviewerReview);

    // 删除评阅教师审阅表
    void deleteReviewerReview(@Param("reviewId") Integer reviewId);

    // 更新评阅教师审阅表
    void updateReviewerReview(ReviewerReview reviewerReview);

    // 查询评阅教师审阅表
    ReviewerReviewDetailsVo selectReviewerReview(@Param("reviewId") Integer reviewId);
}
