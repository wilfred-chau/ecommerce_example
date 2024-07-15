package com.chau.repository;

import com.chau.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10  17:37
 * @Description:
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    // 继承JpaRepository，提供基本的增删改查功能
}
