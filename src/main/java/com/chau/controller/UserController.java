package com.chau.controller;

import com.chau.entity.User;
import com.chau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户对象
     */
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    /**
     * 注册新用户
     *
     * @param user 用户对象
     * @return 用户创建成功消息
     */
    @PostMapping
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "用户创建成功";
    }

    /**
     * 更新用户信息
     *
     * @param userId 用户ID
     * @param user   用户对象
     * @return 用户信息更新成功消息
     */
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        userService.updateUser(user);
        return "用户信息更新成功";
    }
}
