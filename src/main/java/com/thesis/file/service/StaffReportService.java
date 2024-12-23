package com.thesis.file.service;

import com.thesis.file.dao.StaffReportMapper;
import com.thesis.file.entity.TaskBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffReportService {

    @Autowired
    private StaffReportMapper staffReportMapper;

    // 指导老师提交课题任务书相关属性信息 FOR 指导老师
    public void addTaskBook(TaskBook taskBook) {
        if (taskBook == null) {
            throw new RuntimeException("taskBook不能为空");
        }
        staffReportMapper.insertTaskBook(taskBook);
    }

    // 删除课题任务书 FOR 指导老师
    public void deleteTaskBook(Integer taskId) {
        if (taskId == null) {
            throw new RuntimeException("taskId不能为空");
        }
        if (staffReportMapper.selectTaskBook(taskId) == null) {
            throw new RuntimeException("该课题任务书不存在");
        }
        staffReportMapper.deleteTaskBook(taskId);
    }

    // 更新课题任务书 FOR 指导老师
    public void updateTaskBook(TaskBook taskBook) {
        if (taskBook.getTaskId() == null) {
            throw new RuntimeException("taskId不能为空");
        }
        if (staffReportMapper.selectTaskBook(taskBook.getTaskId()) == null) {
            throw new RuntimeException("该课题任务书不存在");
        }
        if (staffReportMapper.selectTaskBook(taskBook.getTaskId()).equals(taskBook)) {
            throw new RuntimeException("更新内容重复");
        }
        staffReportMapper.updateTaskBook(taskBook);
    }

    // ID获取单个指导老师的课题任务书 FOR 管理员、指导老师
    public TaskBook findTaskBook(Integer advisorId) {
        if (advisorId == null) {
            throw new RuntimeException("advisorId不能为空");
        }
        TaskBook taskBook = staffReportMapper.selectTaskBook(advisorId);
        if (taskBook == null) {
            throw new RuntimeException("该课题任务书不存在");
        }
        return taskBook;
    }

    // 专业负责人对课题任务书进行审查给出意见 FOR 专业负责人
    public void updateReviewOpinion(TaskBook taskBook) {
        if (taskBook.getTaskId() == null) {
            throw new RuntimeException("taskId不能为空");
        }
        if (staffReportMapper.selectTaskBook(taskBook.getTaskId()) == null) {
            throw new RuntimeException("该课题任务书不存在");
        }
        staffReportMapper.updateReviewOpinion(taskBook);
    }

    // 专业负责人获取所有课题任务书信息
    public List<TaskBook> findAllTaskBook() {
        return staffReportMapper.selectAllTaskBook();
    }

    // 院领导审查 FOR 院领导
    public void updateLeaderOpinion(TaskBook taskBook) {
        if (taskBook.getTaskId() == null) {
            throw new RuntimeException("taskId不能为空");
        }
        if (staffReportMapper.selectTaskBook(taskBook.getTaskId()) == null) {
            throw new RuntimeException("该课题任务书不存在");
        }
        staffReportMapper.updateLeaderOpinion(taskBook);
    }

    // 指导老师ID获取任务书
    public List<TaskBook> findTaskBookByAdvisorId(Integer advisorId) {
        if (advisorId == null) {
            throw new RuntimeException("advisorId不能为空");
        }
        return staffReportMapper.selectTaskBookByAdvisorId(advisorId);
    }
}
