package com.chau.repository;

import com.chau.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10  17:37
 * @Description:
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 继承JpaRepository，提供基本的增删改查功能
}
