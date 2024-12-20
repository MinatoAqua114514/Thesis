package com.thesis.user.dao;

import com.thesis.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /* 用户管理 */
    void insertUser(User user);

    void deleteUser(@Param("userId") Integer userId);

    void updateUser(User user);

    User selectUser(@Param("userId") Integer userId);
}
