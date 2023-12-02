package aplus.insurancesystem.domain.payment.service;

import aplus.insurancesystem.domain.payment.dto.PaymentInfoResponse;
import java.util.List;

public interface PaymentService {
    // 납입 생성

    List<PaymentInfoResponse> getPaymentList(Long contractId);
}
