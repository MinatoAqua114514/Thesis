package com.thesis.file.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "thesis_writing_standard")
public class ThesisWritingStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer standardId;

    @Column(name = "standard_name")
    private String standardName;

    @Column(name = "file_id")
    private Integer fileId;

    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
