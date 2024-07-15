package com.chau.service.impl;

import com.chau.entity.User;
import com.chau.repository.UserRepository;
import com.chau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 根据用户ID获取用户
     *
     * @param userId 用户ID
     * @return 用户对象
     */
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     */
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * 注册新用户
     *
     * @param user 用户对象
     */
    @Override
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);
        userRepository.save(user);
    }
}
