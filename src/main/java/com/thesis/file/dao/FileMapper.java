package com.thesis.file.dao;

import com.thesis.file.entity.File;
import com.thesis.file.vo.StudentFilesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    // 上传文件属性信息至数据库
    void insertFile(File file);

    // 删除文件属性信息
    void deleteFile(@Param("fileId") Integer fileId);

    // 检查上传者是否有相同file_type的文件
    Integer checkFileExist(File file);

    // 更新文件属性信息
    void updateFile(File file);

    // 获取文件属性信息
    File selectFileById(@Param("fileId") Integer fileId);

    // 获取学生的所有文件
    List<StudentFilesVo> selectStudentFiles(@Param("studentId") Integer studentId);

    List<File> selectAllFiles();

    // 根据文件ID获取文件信息
    File getFileById(Integer fileId);
}
