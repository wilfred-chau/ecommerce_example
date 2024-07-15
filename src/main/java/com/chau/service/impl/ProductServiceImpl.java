package com.chau.service.impl;

import com.chau.entity.Product;
import com.chau.repository.ProductRepository;
import com.chau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 产品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 根据产品ID获取产品
     *
     * @param productId 产品ID
     * @return 产品对象
     */
    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("产品不存在"));
    }

    /**
     * 获取所有产品
     *
     * @return 产品列表
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 创建新产品
     *
     * @param product 产品对象
     */
    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * 更新产品信息
     *
     * @param product 产品对象
     */
    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
