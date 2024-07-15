package com.chau.entity;

import lombok.Data;

import javax.persistence.*;

@Entity  // 表明这是一个JPA实体
@Data  // 使用Lombok生成所有字段的getter和setter方法
@Table(name = "order_details")  // 指定数据库表名为"order_details"
public class OrderDetail {

    @Id  // 指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略为自增
    private Long id;  // 订单详情ID

    private Long orderId;  // 订单ID
    private Long productId;  // 产品ID
    private Integer quantity;  // 购买数量
    private Double price;  // 购买价格
}
