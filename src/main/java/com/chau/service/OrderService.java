package com.chau.service;

import com.chau.entity.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Long userId, Long productId, Integer quantity);

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

    void updateOrderStatus(Long orderId, String status);
}
