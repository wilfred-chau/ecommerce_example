package com.chau.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 18:05
 * @Description: Redis订阅监听器，用于接收Redis频道的消息
 */
@Component  // 标记为Spring组件
@Slf4j  // 提供日志记录功能
public class OrderSubscriber implements MessageListener {

    /**
     * 处理接收到的消息
     *
     * @param message 消息内容
     * @param pattern 订阅的模式（在这里未使用）
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 将消息内容转换为字符串
        String messageBody = new String(message.getBody());

        // 打印日志，记录接收到的消息内容
        log.info("收到消息：{}", messageBody);
    }
}
