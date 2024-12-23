package com.thesis.review.service;

import com.thesis.review.dao.AdvisorReviewMapper;
import com.thesis.review.entity.AdvisorReview;
import com.thesis.review.vo.AdvisorReviewDetailsVo;
import com.thesis.user.dao.StaffMapper;
import com.thesis.user.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdvisorReviewService {

    @Autowired
    private AdvisorReviewMapper advisorReviewMapper;
    @Autowired
    private StaffMapper staffMapper;

    // 提交指导老师审阅表 FOR 指导老师
    public void addAdvisorReview(AdvisorReview advisorReview) {
        if (advisorReview == null) {
            throw new RuntimeException("advisorReview不能为空");
        }
        advisorReviewMapper.insertAdvisorReview(advisorReview);
    }

    // 删除指导老师审阅表 FOR 指导老师
    public void deleteAdvisorReview(Integer reviewId) {
        if (reviewId == null) {
            throw new RuntimeException("reviewId不能为空");
        }
        if (advisorReviewMapper.selectAdvisorReview(reviewId) == null) {
            throw new RuntimeException("该审阅表不存在");
        }
        advisorReviewMapper.deleteAdvisorReview(reviewId);
    }

    // 更新指导老师审阅表 FOR 指导老师
    public void updateAdvisorReview(AdvisorReview advisorReview) {
        if (advisorReview.getReviewId() == null) {
            throw new RuntimeException("reviewId不能为空");
        }
        if (advisorReviewMapper.selectAdvisorReview(advisorReview.getReviewId()) == null) {
            throw new RuntimeException("该审阅表不存在");
        }
        if (advisorReviewMapper.selectAdvisorReview(advisorReview.getReviewId()).equals(advisorReview)) {
            throw new RuntimeException("更新内容重复");
        }
        advisorReviewMapper.updateAdvisorReview(advisorReview);
    }

    // ID查询单个指导老师审阅表 FOR 指导老师
    public List<AdvisorReviewDetailsVo> findAdvisorReview(Integer advisorId) {
        if (advisorId == null) {
            throw new RuntimeException("advisorId不能为空");
        }
        Staff staff = staffMapper.selectStaffById(advisorId);
        if (!Objects.equals(staff.getPosition(), "指导老师")) {
            throw new RuntimeException("该用户不是指导老师");
        }
        List<AdvisorReviewDetailsVo> advisorReview = advisorReviewMapper.selectAdvisorReview(advisorId);
        if (advisorReview == null) {
            throw new RuntimeException("该审阅表不存在");
        }
        return advisorReview;
    }
}
