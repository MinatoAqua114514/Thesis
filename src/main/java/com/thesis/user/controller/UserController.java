package com.thesis.user.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.user.entity.User;
import com.thesis.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID，用于查询特定用户的详细信息
     * @return ApiResponse<User> 包含查询结果的响应对象。若成功，data字段携带User对象；若失败，code和message字段携带错误信息
     */
    @GetMapping("/info")
    public ApiResponse<User> findUserInfoById(Integer userId) {
        try {
            userService.getUserById(userId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(userService.getUserById(userId));
    }

    /**
     * 获取所有用户信息
     *
     * @return ApiResponse<List < User>> 包含所有用户信息的响应对象。若成功，data字段携带User对象列表；若失败，code和message字段携带错误信息
     */
    @GetMapping("/allInfo")
    public ApiResponse<List<User>> findAllUserInfo() {
        try {
            userService.getAllUsers();
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(userService.getAllUsers());
    }

    /**
     * 添加用户
     *
     * @param user 需要添加的用户对象，包含用户的所有信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping("/add")
    public ApiResponse<Void> addUser(User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除指定ID的用户
     *
     * @param userId 用户ID，用于标识待删除的用户
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteUser(Integer userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新用户信息
     *
     * @param user 需要更新的用户对象，包含用户的所有信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/update")
    public ApiResponse<Void> updateUser(User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }




    /**
     * 用户登录接口
     *
     * @param userId  用户ID，用于识别用户
     * @param password 用户密码，用于验证用户身份
     * @param response HTTP响应对象，用于设置登录后产生的cookie信息
     * @return 登录结果，一个Map对象。登录成功时，包含用户角色等信息；
     *         登录失败时，返回一个Map，其中的"error"键携带错误信息。
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam Integer userId, @RequestParam String password, HttpServletResponse response) {
        try {
            Map<String, Object> loginResult = userService.login(userId, password);

            // Create cookies for userId and role
            Cookie userIdCookie = new Cookie("userId", userId.toString());
            String encodedRole = URLEncoder.encode(loginResult.get("role").toString(), StandardCharsets.UTF_8);
            Cookie roleCookie = new Cookie("role", encodedRole);

            // Set cookie properties (optional)
            userIdCookie.setPath("/");
            roleCookie.setPath("/");
            userIdCookie.setHttpOnly(true);
            roleCookie.setHttpOnly(true);

            // Add cookies to response
            response.addCookie(userIdCookie);
            response.addCookie(roleCookie);

            return loginResult;
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }
}
