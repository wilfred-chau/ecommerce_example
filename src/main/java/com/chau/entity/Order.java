package com.chau.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity  // 表明这是一个JPA实体
@Data  // 使用Lombok生成所有字段的getter和setter方法
@Table(name = "orders")  // 指定数据库表名为"orders"
public class Order {

    @Id  // 指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略为自增
    private Long id;  // 订单ID

    private Long productId;  // 产品ID
    private Integer quantity;  // 购买数量

    @Column(name = "created_at", nullable = false, updatable = false)  // 指定数据库字段名为"created_at"，不可为空且不可更新
    private LocalDateTime createdAt;  // 创建时间

    @Column(name = "updated_at")  // 指定数据库字段名为"updated_at"
    private LocalDateTime updatedAt;  // 更新时间

    private String status;  // 订单状态

    private Long userId;  // 用户ID

    @PrePersist  // 在持久化之前执行此方法
    protected void onCreate() {
        createdAt = LocalDateTime.now();  // 设置创建时间为当前时间
        updatedAt = LocalDateTime.now();  // 设置更新时间为当前时间
    }

    @PreUpdate  // 在更新之前执行此方法
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();  // 更新更新时间为当前时间
    }
}
