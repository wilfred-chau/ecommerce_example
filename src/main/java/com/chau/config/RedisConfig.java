package com.chau.config;

import com.chau.util.OrderSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: Redis配置类
 */
@Configuration
public class RedisConfig {

    /**
     * 配置StringRedisTemplate，用于操作Redis中的字符串类型数据。
     *
     * @param redisConnectionFactory Redis连接工厂，用于创建Redis连接。
     * @return 返回一个配置好的StringRedisTemplate实例。
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    /**
     * 配置Redis消息监听容器，用于监听指定主题的消息。
     *
     * @param redisConnectionFactory Redis连接工厂，用于创建Redis连接。
     * @param orderSubscriber       订单订阅者，处理接收到的订单消息。
     * @return 返回一个配置好的RedisMessageListenerContainer实例。
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, OrderSubscriber orderSubscriber) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        // 设置Redis连接工厂
        container.setConnectionFactory(redisConnectionFactory);

        // 添加消息监听器，监听名为"orderChannel"的主题，并将接收到的消息交给orderSubscriber处理。
        container.addMessageListener(orderSubscriber, new PatternTopic("orderChannel"));

        return container;
    }
}
