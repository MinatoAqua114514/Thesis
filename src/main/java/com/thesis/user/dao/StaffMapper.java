package com.thesis.user.dao;

import com.thesis.user.entity.Staff;
import com.thesis.user.vo.StaffDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffMapper {

    /* 教职工信息管理 */
    void insertStaff(Staff staff);

    void deleteStaff(@Param("staffId") Integer staffId);

    void updateStaff(Staff staff);

    StaffDetailsVo selectStaffDetailsById(@Param("staffId") Integer staffId);

    boolean existsById(@Param("staffId") Integer staffId);

    Staff selectStaffById(@Param("staffId") Integer staffId);

    List<StaffDetailsVo> selectAllStaffDetails();
}
