package com.thesis.user.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.user.entity.Staff;
import com.thesis.user.service.StaffService;
import com.thesis.user.vo.StaffDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 添加教职工信息
     *
     * @param staff 需要添加的教职工对象，包含教职工的所有信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping("/add")
    public ApiResponse<Void> addStaff(@RequestBody Staff staff) {
        try {
            staffService.addStaff(staff);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除教职工信息
     *
     * @param staffId 需要删除的教职工ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteStaff(int staffId) {
        try {
            staffService.deleteStaff(staffId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新教职工信息
     *
     * @param staff 需要更新的教职工对象，包含教职工的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/update")
    public ApiResponse<Void> updateStaff(Staff staff) {
        try {
            staffService.updateStaff(staff);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * ID查询单个教职工详细信息
     *
     * @param staffId 需要查询的教职工ID
     * @return ApiResponse<StaffDetailsVo> 包含查询结果的响应对象。若成功，data字段携带StaffDetailsVo对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/details")
    public ApiResponse<StaffDetailsVo> findStaffDetailsById(int staffId) {
        try {
            staffService.findStaffDetailsById(staffId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(staffService.findStaffDetailsById(staffId));
    }

    /**
     * 获取所有教职工详细信息
     *
     * @return ApiResponse<List<StaffDetailsVo>> 包含所有教职工信息的响应对象。若成功，data字段携带StaffDetailsVo对象列表；若失败，code和message字段携带错误信息
     */
    @GetMapping("/allDetails")
    public ApiResponse<List<StaffDetailsVo>> findAllStaffDetails() {
        try {
            staffService.findAllStaffDetails();
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(staffService.findAllStaffDetails());
    }


}
