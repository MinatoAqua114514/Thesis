package com.thesis.file.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.file.entity.File;
import com.thesis.file.service.FileService;
import com.thesis.file.vo.StudentFilesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/file")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private FileService fileService;


    /**
     * 上传文件接口。
     *
     * @param file      需要上传的文件，类型为MultipartFile。
     * @param ownerId   文件所属者的ID，类型为Integer。
     * @param fileType  文件类型，类型为String。
     * @return 返回一个ApiResponse对象，包含操作状态码和消息。
     *                  成功时，状态码为200，消息为"File uploaded successfully!"。
     *                  失败时，状态码为400，消息中包含失败原因。
     */
    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("ownerId") Integer ownerId,
            @RequestParam("fileType") String fileType
    ) {
        try {
            // 创建文件对象
            File fileEntity = new File();
            fileEntity.setOwnerId(ownerId);
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileType(fileType);
            fileEntity.setVersion(1);

            // 调用服务保存文件
            fileService.uploadFile(fileEntity, file.getBytes());
            return ApiResponse.success("File uploaded successfully!");
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage(), null);
        }
    }


    /**
     * 下载文件方法。
     * 根据提供的文件ID查找文件并以附件形式下载。
     *
     * @param fileId 文件ID，用于查询数据库中的文件信息。
     * @return 响应实体（ResponseEntity），包含文件的输入流资源。
     *          如果文件存在且成功准备，则返回200状态码及文件的输入流。
     *          如果文件不存在或发生错误，则可能返回404（未找到）或500（服务器内部错误）状态码。
     */
    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer fileId) {
        try {
            // 获取文件信息
            File file = fileService.getFileById(fileId);
            if (file == null) {
                return ResponseEntity.notFound().build();
            }

            // 加载文件
            java.io.File fileOnDisk = new java.io.File(file.getFilePath());
            if (!fileOnDisk.exists()) {
                return ResponseEntity.notFound().build();
            }

            // 创建输入流
            FileInputStream fileInputStream = new FileInputStream(fileOnDisk);

            // 设置HTTP响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(fileInputStream));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 获取指定学生的文件列表。
     *
     * @param studentId 学生ID，用于查询该学生相关的所有文件信息。
     * @return 返回一个ApiResponse对象，其中数据部分为List<StudentFilesVo>，
     *          包含学生的所有文件信息。如果操作成功，状态码为200；
     *          如果发生异常，状态码为404，并携带错误消息。
     */
    @GetMapping("/list/student")
    public ApiResponse<List<StudentFilesVo>> getStudentFiles(@RequestParam("studentId") Integer studentId) {
        try {
            List<StudentFilesVo> studentFilesVos = fileService.findStudentFiles(studentId);
            return ApiResponse.success(studentFilesVos);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }

    }

    /**
     * 获取所有文件信息。
     *
     * @return ApiResponse<List < File>> 包含所有文件数据的响应，如果成功则包含文件列表，失败则包含错误码和错误信息。
     */
    @GetMapping("/list/all")
    public ApiResponse<List<File>> getAllFiles() {
        try {
            List<File> files = fileService.findAllFiles();
            return ApiResponse.success(files);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

}
