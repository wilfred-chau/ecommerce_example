package com.chau.service.impl;

import com.chau.entity.Payment;
import com.chau.repository.PaymentRepository;
import com.chau.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wilfred
 * @CreateTime: 2024-07-10 17:38
 * @Description: 支付服务实现类
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * 创建支付记录
     *
     * @param payment 支付对象
     */
    @Override
    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    /**
     * 根据支付ID获取支付记录
     *
     * @param paymentId 支付ID
     * @return 支付对象
     */
    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("支付记录不存在"));
    }

    /**
     * 更新支付状态
     *
     * @param paymentId 支付ID
     * @param status 支付状态
     */
    @Override
    public void updatePaymentStatus(Long paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("支付记录不存在"));
        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
