package com.chau.repository;

import com.chau.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-12  18:00
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    // 继承JpaRepository，提供基本的增删改查功能
}
