package com.thesis.review.dao;

import com.thesis.review.entity.AdvisorReview;
import com.thesis.review.vo.AdvisorReviewDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdvisorReviewMapper {

    // 提交指导老师审阅表
    void insertAdvisorReview(AdvisorReview advisorReview);

    // 删除指导老师审阅表
    void deleteAdvisorReview(@Param("reviewId") Integer reviewId);

    // 更新指导老师审阅表
    void updateAdvisorReview(AdvisorReview advisorReview);

    // 查询指导老师审阅表
    List<AdvisorReviewDetailsVo> selectAdvisorReview(@Param("advisorId") Integer advisorId);
}
