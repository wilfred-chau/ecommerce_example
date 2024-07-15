package com.chau.util;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-12  19:43
 * @Description: JWT认证响应类，封装了生成的JWT令牌
 */
public class JwtAuthenticationResponse {
    private String accessToken;  // 访问令牌
    private Long userId;

    // 构造方法，初始化访问令牌
    public JwtAuthenticationResponse(String accessToken, Long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    // 获取访问令牌
    public String getAccessToken() {
        return accessToken;
    }

    // 设置访问令牌
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return userId;
    }
}
