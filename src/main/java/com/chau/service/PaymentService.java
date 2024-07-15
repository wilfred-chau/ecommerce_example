package com.chau.service;

import com.chau.entity.Payment;

public interface PaymentService {

    void createPayment(Payment payment);

    Payment getPaymentById(Long paymentId);

    void updatePaymentStatus(Long paymentId, String status);
}
