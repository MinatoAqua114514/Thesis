package com.thesis.user.vo;

import jakarta.persistence.*;

@Entity
@Table(name = "advisor_students")
public class AdvisorStudentsVo {
    @Id
    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "advisor_name")
    private String advisorName;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "grade")
    private String grade;

    @Column(name = "class")
    private String className;  // "class" 是 Java 的关键字，所以更改为 "className"

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
