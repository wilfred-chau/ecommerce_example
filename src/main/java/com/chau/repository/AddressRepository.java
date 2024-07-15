package com.chau.repository;

import com.chau.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-12  18:00
 * @Description:
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    // 继承JpaRepository，提供基本的增删改查功能

    // 根据用户ID查找所有地址
    List<Address> findAllByUserId(Long userId);
}
