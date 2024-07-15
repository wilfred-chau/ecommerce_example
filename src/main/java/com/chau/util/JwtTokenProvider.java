package com.chau.util;

import com.chau.security.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component  // 标记为Spring的组件，使其可以被Spring容器管理
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")  // 从配置文件中读取JWT密钥
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")  // 从配置文件中读取JWT的过期时间（毫秒）
    private int jwtExpirationInMs;

    private Key key;

    // 构造方法，使用注入的jwtSecret生成密钥
    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // 生成JWT令牌
    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();  // 获取当前用户信息
        Date now = new Date();  // 当前时间
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);  // 令牌过期时间

        // 构建JWT令牌
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))  // 设置主题为用户ID
                .setIssuedAt(now)  // 设置签发时间
                .setExpiration(expiryDate)  // 设置过期时间
                .signWith(key, SignatureAlgorithm.HS512)  // 使用HS512算法签名
                .compact();
    }

    // 验证JWT令牌
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);  // 解析令牌
            return true;  // 如果令牌有效，返回true
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Invalid JWT token", e);  // 捕获解析异常，记录错误日志
        }
        return false;  // 如果令牌无效，返回false
    }

    // 从JWT令牌中获取用户ID
    public Long getUserIdFromJWT(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();  // 解析令牌主体
            return Long.parseLong(claims.getSubject());  // 返回用户ID
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Error extracting user id from JWT", e);  // 捕获解析异常，记录错误日志
            return null;  // 如果解析失败，返回null
        }
    }
}
