package com.thesis.file.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.file.entity.ThesisWritingStandard;
import com.thesis.file.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/standard")
public class StandardController {

    @Autowired
    private StandardService standardService;

    /**
     * 添加论文写作规范
     *
     * @param standard 需要添加的论文写作规范对象，包含论文写作规范的所有信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping
    public ApiResponse<Void> addStandard(ThesisWritingStandard standard) {
        try {
            standardService.addThesisWritingStandard(standard);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除论文写作规范
     *
     * @param standardId 需要删除的论文写作规范ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping
    public ApiResponse<Void> deleteStandard(int standardId) {
        try {
            standardService.deleteThesisWritingStandard(standardId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新论文写作规范
     *
     * @param standard 需要更新的论文写作规范对象，包含论文写作规范的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping
    public ApiResponse<Void> updateStandard(ThesisWritingStandard standard) {
        try {
            standardService.updateThesisWritingStandard(standard);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 获取论文写作规范
     *
     * @return ApiResponse<ThesisWritingStandard> 获取操作的响应结果。成功时，返回状态码200及论文写作规范对象；失败时，返回状态码404及错误信息
     */
    @GetMapping
    public ApiResponse<ThesisWritingStandard> getStandard() {
        try {
            // 无需传入参数，直接调用service层方法，默认获取ID = 1的论文写作规范
            ThesisWritingStandard standard = standardService.findThesisWritingStandard();
            return ApiResponse.success(standard);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }
}
