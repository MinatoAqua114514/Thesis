package com.thesis.review.vo;

import jakarta.persistence.*;

@Entity
@Table(name = "student_grades")
public class StudentGradesVo {
    @Id
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "advisor_score")
    private Integer advisorScore;

    @Column(name = "reviewer_score")
    private String reviewerScore;

    @Column(name = "defense_score")
    private Integer defenseScore;

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
}
