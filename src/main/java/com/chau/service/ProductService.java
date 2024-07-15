package com.chau.service;

import com.chau.entity.Product;

import java.util.List;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-12  17:42
 * @Description:
 */
public interface ProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void createProduct(Product product);

    void updateProduct(Product product);
}
