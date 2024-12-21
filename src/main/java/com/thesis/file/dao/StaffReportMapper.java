package com.thesis.file.dao;

import com.thesis.file.entity.TaskBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StaffReportMapper {

    // 指导老师提交课题任务书相关属性信息
    void insertTaskBook(TaskBook taskBook);

    // 删除课题任务书
    void deleteTaskBook(@Param("taskId") Integer taskId);

    // 更新课题任务书
    void updateTaskBook(TaskBook taskBook);

    // 获取指导老师的课题任务书
    TaskBook selectTaskBook(@Param("advisorId") Integer advisorId);

    // 专业负责人对课题任务书进行审查给出意见
    void updateReviewOpinion(TaskBook taskBook);

    // 院领导审查
    void updateLeaderOpinion(TaskBook taskBook);
}
