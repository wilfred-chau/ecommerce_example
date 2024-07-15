package com.chau.entity;

import lombok.Data;

import javax.persistence.*;

@Entity  // 表明这是一个JPA实体
@Data  // 使用Lombok生成所有字段的getter和setter方法
@Table(name = "addresses")  // 指定数据库表名为"addresses"
public class Address {

    @Id  // 指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键生成策略为自增
    private Long id;  // 地址ID

    private Long userId;  // 用户ID
    private String address;  // 地址
    private String city;  // 城市
    private String state;  // 州/省
    private String zipCode;  // 邮政编码
    private String country;  // 国家
}
