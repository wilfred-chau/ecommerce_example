package com.chau.entity;

import lombok.Data;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-15  20:59
 * @Description:
 */
@Data
public class LoginRequest {
    private String username;
    private String password;

    // getters and setters
}
