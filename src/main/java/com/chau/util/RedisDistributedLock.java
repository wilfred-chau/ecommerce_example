package com.chau.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:43
 * @Description: Redis分布式锁工具类，实现分布式环境下的锁机制
 */
@Component  // 标记为Spring组件，使其可以被Spring容器管理
public class RedisDistributedLock {

    private final StringRedisTemplate stringRedisTemplate;  // 用于操作Redis的模板

    @Autowired  // 构造函数注入StringRedisTemplate
    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁的键
     * @param requestId  请求标识，用于区分不同请求
     * @param expireTime 锁的过期时间，单位为毫秒
     * @return 是否获取成功
     */
    public boolean tryGetLock(String lockKey, String requestId, long expireTime) {
        // 使用Redis的setIfAbsent方法尝试设置锁，设置成功则获取锁成功
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, TimeUnit.MILLISECONDS);
        return result != null && result;  // 返回获取锁的结果
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁的键
     * @param requestId 请求标识，用于验证是否是持有锁的请求
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) {
        // 获取当前锁的持有者标识
        String value = stringRedisTemplate.opsForValue().get(lockKey);
        // 如果请求标识与当前持有者标识一致，则删除锁并释放
        if (requestId.equals(value)) {
            stringRedisTemplate.delete(lockKey);
            return true;  // 返回释放锁的结果
        }
        return false;  // 请求标识不一致，返回释放锁失败
    }
}
