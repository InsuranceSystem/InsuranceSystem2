package aplus.insurancesystem2.domain.payment.service;


import aplus.insurancesystem2.domain.payment.dto.PaymentListResponse;

public interface PaymentService {
    // 납입 생성

    PaymentListResponse getAllPayment(String customerId);
}
