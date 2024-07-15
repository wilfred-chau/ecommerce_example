package com.chau.controller;

import com.chau.entity.Order;
import com.chau.entity.Product;
import com.chau.service.OrderService;
import com.chau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 订单控制器类
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    /**
     * 创建订单
     *
     * @param userId 用户ID
     * @param productId 产品ID
     * @param quantity 数量
     * @return 成功创建消息
     */
    @PostMapping("/create")
    public String createOrder(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        orderService.createOrder(userId, productId, quantity);
        return "订单创建成功";
    }

    /**
     * 根据订单ID获取订单
     *
     * @param orderId 订单ID
     * @return 订单对象
     */
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     * 获取所有订单
     *
     * @return 订单列表
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * 更新订单状态
     *
     * @param orderId 订单ID
     * @param status 订单状态
     * @return 成功更新消息
     */
    @PutMapping("/{orderId}/status")
    public String updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "订单状态更新成功";
    }

    /**
     * 获取所有产品
     *
     * @return 产品列表
     */
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    /**
     * 根据产品ID获取产品
     *
     * @param productId 产品ID
     * @return 产品对象
     */
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}
