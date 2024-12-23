package com.thesis.file.service;

import com.thesis.file.dao.FileMapper;
import com.thesis.file.entity.File;
import com.thesis.file.vo.StudentFilesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Value("${upload.directory:upload}")
    private String uploadDirectory;

    @Autowired
    private FileMapper fileMapper;

    // 上传文件属性信息至数据库 FOR all
    public void addFile(File file) {
        fileMapper.insertFile(file);
    }

    // 删除文件属性信息 FOR all
    public void deleteFile(Integer fileId) {
        if (fileMapper.selectFileById(fileId) == null) {
            throw new RuntimeException("删除失败，文件不存在");
        }
        fileMapper.deleteFile(fileId);
    }

    // 更新文件属性信息 FOR all
    public void updateFile(File file) {
        if (fileMapper.selectFileById(file.getFileId()) == null) {
            throw new RuntimeException("更新失败，文件不存在");
        }
        if (fileMapper.checkFileExist(file) != 0) {
            fileMapper.updateFile(file);
        }
    }

    // ID获取文件属性信息 FOR all
    public File findFile(Integer fileId) {
        if (fileMapper.selectFileById(fileId) == null) {
            throw new RuntimeException("获取失败，文件不存在");
        }
        return fileMapper.selectFileById(fileId);
    }

    // 检查上传者是否有相同file_type的文件 FOR all
    public Integer checkFileExist(File file) {
        return fileMapper.checkFileExist(file);
    }

    // 获取学生的所有文件的详细信息 FOR ALL
    public List<StudentFilesVo> findStudentFiles(Integer studentId) {
        return fileMapper.selectStudentFiles(studentId);
    }

    // 获取所有文件 FOR 管理员
    public List<File> findAllFiles() {
        return fileMapper.selectAllFiles();
    }









    public File uploadFile(File file, byte[] fileContent) throws Exception {
        // 保存文件到本地
        java.io.File uploadDir = new java.io.File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String filePath = uploadDirectory + "/" + file.getFileName();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileContent);
        } catch (IOException e) {
            throw new Exception("Failed to save file", e);
        }

        // 设置文件路径并插入数据库
        file.setFilePath(filePath);
        try {
            fileMapper.insertFile(file);
        } catch (Exception e) {
            throw new Exception("上传文件失败，当前文件名的文件已存在", e);
        }

        // 返回上传文件的ID
        file.setFileId(fileMapper.selectFileByPath(filePath).getFileId());

        return file;
    }

    public File getFileById(Integer fileId) {
        if (fileMapper.selectFileById(fileId) == null) {
            throw new RuntimeException("未找到文件ID为: " + fileId + "的文件");
        }
        return fileMapper.selectFileById(fileId); // 从数据库查询文件信息
    }

    // 根据文件路径查找文件ID
    public Integer getFileIdByPath(String filePath) {
        if (fileMapper.selectFileByPath(filePath) == null) {
            throw new RuntimeException("未找到路径为: " + filePath + "的文件");
        }
        return fileMapper.selectFileIdByPath(filePath);
    }
}
