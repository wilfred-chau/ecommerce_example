package com.chau.service;

import com.chau.entity.User;

public interface UserService {

    User getUserById(Long userId);

    void updateUser(User user);

    void registerUser(User user);

}
