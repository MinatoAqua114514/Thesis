package com.thesis.user.service;

import com.thesis.user.dao.UserMapper;
import com.thesis.user.entity.Staff;
import com.thesis.user.entity.Student;
import com.thesis.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 添加新用户 FOR 管理员
    public void addUser(User user) {
        if (userMapper.existsById(user.getUserId())) {
            throw new RuntimeException("添加失败，用户已存在");
        }
        userMapper.insertUser(user);
    }

    // 删除用户 FOR 管理员
    public void deleteUser(int userId) {
        if (!userMapper.existsById(userId)) {
            throw new RuntimeException("删除失败，用户不存在");
        }
        userMapper.deleteUser(userId);
    }

    // 更新用户的基本信息 FOR 管理员
    public void updateUser(User user) {
        if (!userMapper.existsById(user.getUserId())) {
            throw new RuntimeException("更新失败，用户不存在");
        }
        // 和数据库中的信息比对，是否完全重复
        User userInDB = userMapper.selectUserById(user.getUserId());
        if (user.equals(userInDB)) {
            throw new RuntimeException("更新失败，用户信息未发生变化");
        }
        userMapper.updateUser(user);
    }

    // 查找用户基本信息 FOR 管理员
    public User getUserById(int userId) {
        if (!userMapper.existsById(userId)) {
            throw new RuntimeException("查找失败，用户不存在");
        }
        return userMapper.selectUserById(userId);
    }

    // 获取所有用户基本信息 FOR 管理员
    public List<User> getAllUsers() {
        return userMapper.selectAllUser();
    }





    public Map<String, Object> login(Integer userId, String password) throws Exception {
        // 查询用户信息
        User user = userMapper.getUserInfo(userId);
        if (user == null) {
            throw new Exception("用户不存在");
        }

        // 校验密码
        if (!user.getPassword().equals(password)) {
            throw new Exception("密码错误");
        }

        // 日志记录控制台输出user信息
        System.out.println(user);

        // 根据角色获取更多信息
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getUserId());
        result.put("username", user.getUsername());
        result.put("role", user.getRole());

        if ("学生".equals(user.getRole())) {
            Student student = userMapper.getStudentInfo(userId);
            result.put("grade", student.getGrade());
            result.put("class", student.getClassName());

            // 日志记录控制台输出student信息
            System.out.println(student);


        } else if ("教职工".equals(user.getRole())) {
            Staff staff = userMapper.getStaffInfo(userId);
            result.put("position", staff.getPosition());

            // 日志记录控制台输出staff信息
            System.out.println(staff);
        }

        return result;
    }
}
