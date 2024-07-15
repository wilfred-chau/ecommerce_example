package com.chau.entity;

import lombok.Data;

import javax.persistence.*;

@Entity  // 表明这是一个JPA实体
@Data  // 使用Lombok生成所有字段的getter和setter方法
@Table(name = "product")  // 指定数据库表名为"product"
public class Product {

    @Id  // 指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略为自增
    private Long id;  // 产品ID

    private String name;  // 产品名称
    private Integer stock;  // 库存数量

    private String description;  // 产品描述
    private Double price;  // 产品价格
}
