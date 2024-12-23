package com.thesis.file.dto;

public class FileStatusDTO {

    // 学号
    private Integer studentId;

    // 学生姓名
    private String studentName;

    // 指导老师评分
    private Integer advisorScore;

    // 评阅老师评分
    private String reviewerScore;

    // 答辩评分
    private Integer defenseScore;

    // 选题申报题目和状态
    public String submissionTitle;

    public String submissionStatus;


    // 开题报告题目和状态
    public String openingTitle;

    public String openingStatus;


    // 中期检查题目和状态
    public String middleTitle;

    public String middleStatus;


    // 论文题目和状态
    public String thesisTitle;

    public String thesisStatus;


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAdvisorScore() {
        return advisorScore;
    }

    public void setAdvisorScore(Integer advisorScore) {
        this.advisorScore = advisorScore;
    }

    public String getReviewerScore() {
        return reviewerScore;
    }

    public void setReviewerScore(String reviewerScore) {
        this.reviewerScore = reviewerScore;
    }

    public Integer getDefenseScore() {
        return defenseScore;
    }

    public void setDefenseScore(Integer defenseScore) {
        this.defenseScore = defenseScore;
    }

    public String getSubmissionTitle() {
        return submissionTitle;
    }

    public void setSubmissionTitle(String submissionTitle) {
        this.submissionTitle = submissionTitle;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getOpeningTitle() {
        return openingTitle;
    }

    public void setOpeningTitle(String openingTitle) {
        this.openingTitle = openingTitle;
    }

    public String getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(String openingStatus) {
        this.openingStatus = openingStatus;
    }

    public String getMiddleTitle() {
        return middleTitle;
    }

    public void setMiddleTitle(String middleTitle) {
        this.middleTitle = middleTitle;
    }

    public String getMiddleStatus() {
        return middleStatus;
    }

    public void setMiddleStatus(String middleStatus) {
        this.middleStatus = middleStatus;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getThesisStatus() {
        return thesisStatus;
    }

    public void setThesisStatus(String thesisStatus) {
        this.thesisStatus = thesisStatus;
    }
}
