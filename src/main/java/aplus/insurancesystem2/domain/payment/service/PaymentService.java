package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.payment.dto.PaymentInfoResponse;
import java.util.List;

public interface PaymentService {
    // 납입 생성

    List<PaymentInfoResponse> getPaymentList(Long contractId);
}
