package com.thesis.user.dao;

import com.thesis.user.entity.Staff;
import com.thesis.user.entity.Student;
import com.thesis.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /* 用户管理 */
    void insertUser(User user);

    void deleteUser(@Param("userId") Integer userId);

    void updateUser(User user);

    User selectUserById(@Param("userId") Integer userId);

    boolean existsById(@Param("userId") Integer userId);

    List<User> selectAllUser();




    // 获取用户基本信息
    User getUserInfo(@Param("userId") Integer userId);

    // 获取学生信息
    Student getStudentInfo(@Param("userId") Integer userId);

    // 获取教职工信息
    Staff getStaffInfo(@Param("userId") Integer userId);

    // 用户名获取用户信息
    User selectUserByUsername(@Param("username") String username);
}
