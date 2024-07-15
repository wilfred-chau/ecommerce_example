package com.chau.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity  // 表明这是一个JPA实体
@Data  // 使用Lombok生成所有字段的getter和setter方法
@Table(name = "payments")  // 指定数据库表名为"payments"
public class Payment {

    @Id  // 指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略为自增
    private Long id;  // 支付ID

    private Long orderId;  // 订单ID
    private Double amount;  // 支付金额
    private String paymentMethod;  // 支付方式
    private String status;  // 支付状态

    @Column(name = "created_at", nullable = false, updatable = false)  // 指定数据库字段名为"created_at"，不可为空且不可更新
    private LocalDateTime createdAt;  // 创建时间

    @PrePersist  // 在持久化之前执行此方法
    protected void onCreate() {
        createdAt = LocalDateTime.now();  // 设置创建时间为当前时间
    }
}
