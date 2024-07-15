package com.chau.controller;

import com.chau.entity.Payment;
import com.chau.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 支付控制器类
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 创建支付记录
     *
     * @param payment 支付对象
     * @return 成功创建消息
     */
    @PostMapping
    public String createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);
        return "支付记录创建成功";
    }

    /**
     * 根据支付ID获取支付记录
     *
     * @param paymentId 支付ID
     * @return 支付对象
     */
    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    /**
     * 更新支付状态
     *
     * @param paymentId 支付ID
     * @param status 支付状态
     * @return 成功更新消息
     */
    @PutMapping("/{paymentId}/status")
    public String updatePaymentStatus(@PathVariable Long paymentId, @RequestParam String status) {
        paymentService.updatePaymentStatus(paymentId, status);
        return "支付状态更新成功";
    }
}
