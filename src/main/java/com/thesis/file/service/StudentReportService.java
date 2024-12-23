package com.thesis.file.service;

import com.thesis.file.dto.FileStatusDTO;
import com.thesis.file.dao.StudentReportMapper;
import com.thesis.file.entity.MiddleReport;
import com.thesis.file.entity.OpeningReport;
import com.thesis.file.entity.Thesis;
import com.thesis.file.entity.TopicSubmission;
import com.thesis.user.dao.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentReportService {

    @Autowired
    private StudentReportMapper studentReportMapper;
    @Autowired
    private StaffMapper staffMapper;

    /* 1.选题申报 */
    // 提交选题申报描述信息等 FOR 学生
    public void addTopicSubmission(TopicSubmission topicSubmission) {
        studentReportMapper.insertTopicSubmission(topicSubmission);
    }

    // 删除选题申报 FOR 学生
    public void deleteTopicSubmission(Integer topicId) {
        if (topicId == null) {
            throw new RuntimeException("topicId不能为空");
        }
        if (studentReportMapper.selectTopicSubmission(topicId) == null) {
            throw new RuntimeException("该选题申报不存在");
        }
        studentReportMapper.deleteTopicSubmission(topicId);
    }

    // 更新选题申报 FOR 学生
    public void updateTopicSubmission(TopicSubmission topicSubmission) {
        if (topicSubmission.getTopicId() == null) {
            throw new RuntimeException("topicId不能为空");
        }
        if (studentReportMapper.selectTopicSubmission(topicSubmission.getTopicId()) == null) {
            throw new RuntimeException("该选题申报不存在");
        }
        // 更新内容重复
        if (studentReportMapper.selectTopicSubmission(topicSubmission.getTopicId()).equals(topicSubmission)) {
            throw new RuntimeException("更新内容重复");
        }
        studentReportMapper.updateTopicSubmission(topicSubmission);
    }

    // 文件ID查看选题申报 FOR ALL
    public TopicSubmission findTopicSubmission(Integer topicId) {
        if (topicId == null) {
            throw new RuntimeException("topicId不能为空");
        }
        if (studentReportMapper.selectTopicSubmission(topicId) == null) {
            throw new RuntimeException("该选题申报不存在");
        }
        return studentReportMapper.selectTopicSubmission(topicId);
    }

    // 学生ID查看选题申报 FOR 学生
    public TopicSubmission findTopicSubmissionByStudentId(Integer studentId) {
        if (studentId == null) {
            throw new RuntimeException("studentId不能为空");
        }
        if (studentReportMapper.selectTopicSubmissionByStudentId(studentId) == null) {
            throw new RuntimeException("该学生未提交选题申报");
        }
        return studentReportMapper.selectTopicSubmissionByStudentId(studentId);
    }

    // 专业负责人对选题申报进行审核给出意见 FOR 专业负责人
    public void updateReviewOpinion(TopicSubmission topicSubmission) {
        if (topicSubmission.getTopicId() == null) {
            throw new RuntimeException("topicId不能为空");
        }
        if (studentReportMapper.selectTopicSubmission(topicSubmission.getTopicId()) == null) {
            throw new RuntimeException("该选题申报不存在");
        }
        studentReportMapper.updateReviewOpinion(topicSubmission);
    }

    // 院领导审查 FOR 院领导
    public void updateLeaderOpinion(TopicSubmission topicSubmission) {
        if (topicSubmission.getTopicId() == null) {
            throw new RuntimeException("topicId不能为空");
        }
        if (studentReportMapper.selectTopicSubmission(topicSubmission.getTopicId()) == null) {
            throw new RuntimeException("该选题申报不存在");
        }
        studentReportMapper.updateLeaderOpinion(topicSubmission);
    }



    /* 2.开题报告 */
    // 提交开题报告 FOR 学生
    public void addOpeningReport(OpeningReport openingReport) {
        studentReportMapper.insertOpeningReport(openingReport);
    }

    // 删除开题报告 FOR 学生
    public void deleteOpeningReport(Integer reportId) {
        if (reportId == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectOpeningReport(reportId) == null) {
            throw new RuntimeException("该开题报告不存在");
        }
        studentReportMapper.deleteOpeningReport(reportId);
    }

    // 更新开题报告 FOR 学生
    public void updateOpeningReport(OpeningReport openingReport) {
        if (openingReport.getReportId() == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectOpeningReport(openingReport.getReportId()) == null) {
            throw new RuntimeException("该开题报告不存在");
        }
        if (studentReportMapper.selectOpeningReport(openingReport.getReportId()).equals(openingReport)) {
            throw new RuntimeException("更新内容重复");
        }
        studentReportMapper.updateOpeningReport(openingReport);
    }

    // 文件ID查看开题报告 FOR ALL
    public OpeningReport findOpeningReport(Integer reportId) {
        if (reportId == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectOpeningReport(reportId) == null) {
            throw new RuntimeException("该开题报告不存在");
        }
        return studentReportMapper.selectOpeningReport(reportId);
    }

    // 学生ID查看开题报告 FOR 学生
    public OpeningReport findOpeningReportByStudentId(Integer studentId) {
        if (studentId == null) {
            throw new RuntimeException("studentId不能为空");
        }
        if (studentReportMapper.selectOpeningReportByStudentId(studentId) == null) {
            throw new RuntimeException("该学生未提交开题报告");
        }
        return studentReportMapper.selectOpeningReportByStudentId(studentId);
    }


    // 专业负责人对开题报告进行审核给出意见
    public void updateOpeningReviewOpinion(OpeningReport openingReport) {
        if (openingReport.getReportId() == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectOpeningReport(openingReport.getReportId()) == null) {
            throw new RuntimeException("该开题报告不存在");
        }
        studentReportMapper.updateOpeningReviewOpinion(openingReport);
    }



    /* 3.中期报告 */
    // 提交中期报告 FOR 学生
    public void addMiddleReport(MiddleReport middleReport) {
        studentReportMapper.insertMiddleReport(middleReport);
    }

    // 删除中期报告 FOR 学生
    public void deleteMiddleReport(Integer reportId) {
        if (reportId == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectMiddleReport(reportId) == null) {
            throw new RuntimeException("该中期报告不存在");
        }
        studentReportMapper.deleteMiddleReport(reportId);
    }

    // 更新中期报告 FOR 学生
    public void updateMiddleReport(MiddleReport middleReport) {
        if (middleReport.getReportId() == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectMiddleReport(middleReport.getReportId()) == null) {
            throw new RuntimeException("该中期报告不存在");
        }
        if (studentReportMapper.selectMiddleReport(middleReport.getReportId()).equals(middleReport)) {
            throw new RuntimeException("更新内容重复");
        }
        studentReportMapper.updateMiddleReport(middleReport);
    }

    // 文件ID查看中期报告
    public MiddleReport findMiddleReport(Integer reportId) {
        if (reportId == null) {
            throw new RuntimeException("reportId不能为空");
        }
        if (studentReportMapper.selectMiddleReport(reportId) == null) {
            throw new RuntimeException("该中期报告不存在");
        }
        return studentReportMapper.selectMiddleReport(reportId);
    }

    // 学生ID查看中期报告 FOR 学生
    public MiddleReport findMiddleReportByStudentId(Integer studentId) {
        if (studentId == null) {
            throw new RuntimeException("studentId不能为空");
        }
        if (studentReportMapper.selectMiddleReportByStudentId(studentId) == null) {
            throw new RuntimeException("该学生未提交中期报告");
        }
        return studentReportMapper.selectMiddleReportByStudentId(studentId);
    }



    /* 4.论文 */
    // 提交论文 FOR 学生
    public void addThesis(Thesis thesis) {
        studentReportMapper.insertThesis(thesis);
    }

    // 删除论文 FOR 学生
    public void deleteThesis(Integer thesisId) {
        if (thesisId == null) {
            throw new RuntimeException("thesisId不能为空");
        }
        if (studentReportMapper.selectThesis(thesisId) == null) {
            throw new RuntimeException("该论文不存在");
        }
        studentReportMapper.deleteThesis(thesisId);
    }

    // 更新论文 FOR 学生
    public void updateThesis(Thesis thesis) {
        if (thesis.getThesisId() == null) {
            throw new RuntimeException("thesisId不能为空");
        }
        if (studentReportMapper.selectThesis(thesis.getThesisId()) == null) {
            throw new RuntimeException("该论文不存在");
        }
        if (studentReportMapper.selectThesis(thesis.getThesisId()).equals(thesis)) {
            throw new RuntimeException("更新内容重复");
        }
        studentReportMapper.updateThesis(thesis);
    }

    // 文件ID查看论文 FOR ALL
    public Thesis findThesis(Integer thesisId) {
        if (thesisId == null) {
            throw new RuntimeException("thesisId不能为空");
        }
        if (studentReportMapper.selectThesis(thesisId) == null) {
            throw new RuntimeException("该论文不存在");
        }
        return studentReportMapper.selectThesis(thesisId);
    }

    // 学生ID查看论文 FOR 学生
    public Thesis findThesisByStudentId(Integer studentId) {
        if (studentId == null) {
            throw new RuntimeException("studentId不能为空");
        }
        if (studentReportMapper.selectThesisByStudentId(studentId) == null) {
            throw new RuntimeException("该学生未提交论文");
        }
        return studentReportMapper.selectThesisByStudentId(studentId);
    }

    // 指导老师ID获取指导学生所有文件状态和评分
    public List<FileStatusDTO> findStudentReportStatusByAdvisor(Integer advisorId) {
        if (advisorId == null) {
            throw new RuntimeException("advisorId不能为空");
        }
        // 指导老师不存在
        if (staffMapper.selectStaffById(advisorId) == null) {
            throw new RuntimeException("该指导老师不存在");
        }
        return studentReportMapper.selectStudentSubmissionStatusByTeacherId(advisorId);
    }

    // 获取所有学生文件状态和评分
    public List<FileStatusDTO> findAllStudentReportStatus() {
        return studentReportMapper.selectAllStudentSubmissionStatus();
    }
}
