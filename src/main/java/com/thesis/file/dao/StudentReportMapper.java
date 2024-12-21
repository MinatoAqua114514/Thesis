package com.thesis.file.dao;

import com.thesis.file.entity.MiddleReport;
import com.thesis.file.entity.OpeningReport;
import com.thesis.file.entity.Thesis;
import com.thesis.file.entity.TopicSubmission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentReportMapper {

    /* 1.选题申报 */
    // 提交选题申报描述信息等
    void insertTopicSubmission(TopicSubmission topicSubmission);

    // 删除选题申报
    void deleteTopicSubmission(@Param("topicId") Integer topicId);

    // 更新选题申报
    void updateTopicSubmission(TopicSubmission topicSubmission);

    // 查看选题申报
    TopicSubmission selectTopicSubmission(@Param("topicId") Integer topicId);

    // 专业负责人对选题申报进行审核给出意见
    void updateReviewOpinion(TopicSubmission topicSubmission);

    // 院领导审查
    void updateLeaderOpinion(TopicSubmission topicSubmission);

    // 学生ID获取选题申报
    TopicSubmission selectTopicSubmissionByStudentId(@Param("studentId") Integer studentId);


    /* 2.开题报告 */
    // 提交开题报告
    void insertOpeningReport(OpeningReport openingReport);

    // 删除开题报告
    void deleteOpeningReport(@Param("reportId") Integer reportId);

    // 更新开题报告
    void updateOpeningReport(OpeningReport openingReport);

    // 查看开题报告
    OpeningReport selectOpeningReport(@Param("reportId") Integer reportId);

    // 专业负责人对开题报告进行审核给出意见
    void updateOpeningReviewOpinion(OpeningReport openingReport);

    // 学生ID获取开题报告
    OpeningReport selectOpeningReportByStudentId(@Param("studentId") Integer studentId);


    /* 3.中期报告 */
    // 提交中期报告
    void insertMiddleReport(MiddleReport middleReport);

    // 删除中期报告
    void deleteMiddleReport(@Param("reportId") Integer reportId);

    // 更新中期报告
    void updateMiddleReport(MiddleReport middleReport);

    // 查看中期报告
    MiddleReport selectMiddleReport(@Param("reportId") Integer reportId);

    // 学生ID获取中期报告
    MiddleReport selectMiddleReportByStudentId(@Param("studentId") Integer studentId);



    /* 4.论文 */
    // 提交论文
    void insertThesis(Thesis thesis);

    // 删除论文
    void deleteThesis(@Param("thesisId") Integer thesisId);

    // 更新论文
    void updateThesis(Thesis thesis);

    // 查看论文
    Thesis selectThesis(@Param("thesisId") Integer thesisId);

    // 学生ID获取论文
    Thesis selectThesisByStudentId(@Param("studentId") Integer studentId);
}
