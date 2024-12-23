package com.thesis.user.service;

import com.thesis.user.dao.StaffMapper;
import com.thesis.user.dao.UserMapper;
import com.thesis.user.entity.Staff;
import com.thesis.user.vo.StaffDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private UserMapper userMapper;

    // 添加教职工信息 FOR 管理员
    public void addStaff(Staff staff) {
        // 判断staffID是否是教职工角色
        String staffRole = userMapper.getUserInfo(staff.getStaffId()).getRole();
        if (!staffRole.equals("staff")) {
            throw new RuntimeException("添加失败，该ID不是教职工");
        }
        if (staffMapper.existsById(staff.getStaffId())) {
            throw new RuntimeException("添加失败，教职工已存在");
        }
        staffMapper.insertStaff(staff);
    }

    // 删除教职工信息 FOR 管理员
    public void deleteStaff(int staffId) {
        if (!staffMapper.existsById(staffId)) {
            throw new RuntimeException("删除失败，教职工不存在");
        }
        staffMapper.deleteStaff(staffId);
    }

    // 更新教职工信息 FOR 管理员
    public void updateStaff(Staff staff) {
        if (!staffMapper.existsById(staff.getStaffId())) {
            throw new RuntimeException("更新失败，教职工不存在");
        }
        if (staff.equals(staffMapper.selectStaffById(staff.getStaffId()))) {
            throw new RuntimeException("更新失败，教职工信息未发生变化");
        }
        staffMapper.updateStaff(staff);
    }

    // ID查询单个教职工详细信息 FOR 管理员、教职工
    public StaffDetailsVo findStaffDetailsById(int staffId) {
        if (!staffMapper.existsById(staffId)) {
            throw new RuntimeException("查找失败，教职工不存在");
        }
        return staffMapper.selectStaffDetailsById(staffId);
    }

    // 获取所有教职工详细信息 FOR 管理员
    public List<StaffDetailsVo> findAllStaffDetails() {
        return staffMapper.selectAllStaffDetails();
    }

}
