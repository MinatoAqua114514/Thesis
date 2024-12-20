package com.thesis.user.service;

import com.thesis.user.dao.UserMapper;
import com.thesis.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUser(Integer userId) {
        return userMapper.selectUser(userId);
    }
}
