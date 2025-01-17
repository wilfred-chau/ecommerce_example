package com.chau.service.impl;

import com.chau.entity.Order;
import com.chau.entity.Product;
import com.chau.entity.User;
import com.chau.repository.OrderRepository;
import com.chau.repository.ProductRepository;
import com.chau.repository.UserRepository;
import com.chau.service.OrderService;
import com.chau.util.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 订单服务实现类 (异步处理)
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RedisDistributedLock redisDistributedLock;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    private static final String LOCK_KEY_PREFIX = "lock:user:";
    private static final String LOCK_KEY_SUFFIX = "product:";

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, RedisDistributedLock redisDistributedLock, UserRepository userRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.redisDistributedLock = redisDistributedLock;
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
    }

    /**
     * 创建订单 (异步处理)
     *
     * @param userId 用户ID
     * @param productId 产品ID
     * @param quantity 数量
     */
    @Override
    @Transactional
    public void createOrder(Long userId, Long productId, Integer quantity) {
        String lockKey = LOCK_KEY_PREFIX + userId + "_" + LOCK_KEY_SUFFIX + productId;
        String requestId = UUID.randomUUID().toString();

        try {
            boolean lockAcquired = redisDistributedLock.tryGetLock(lockKey, requestId, 5000);
            if (!lockAcquired) {
                throw new RuntimeException("获取锁失败");
            }

            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("产品不存在"));

            if (product.getStock() < quantity) {
                throw new RuntimeException("库存不足");
            }

            product.setStock(product.getStock() - quantity);
            productRepository.save(product);

            Order order = new Order();
            order.setProductId(productId);
            order.setQuantity(quantity);
            order.setStatus("Pending");
            order.setUserId(userId);
            orderRepository.save(order);

            // 异步发送订单创建成功的消息到 RabbitMQ
            log.info("Sending order created message: {}", order);
            rabbitTemplate.convertAndSend("orderExchange", "order.created", order);

        } finally {
            redisDistributedLock.releaseLock(lockKey, requestId);
        }
    }

    /**
     * 根据订单ID获取订单
     *
     * @param orderId 订单ID
     * @return 订单对象
     */
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("订单不存在"));
    }

    /**
     * 获取所有订单
     *
     * @return 订单列表
     */
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * 更新订单状态 (异步处理)
     *
     * @param orderId 订单ID
     * @param status 订单状态
     */
    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("订单不存在"));
        order.setStatus(status);
        orderRepository.save(order);

        // 异步发送订单状态更新的消息到 RabbitMQ
        log.info("Sending order status updated message: {}", order);
        rabbitTemplate.convertAndSend("orderExchange", "order.status.updated", order);
    }
}
