package aplus.insurancesystem.domain.payment.service;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.contract.entity.Contract;
import aplus.insurancesystem.domain.payment.dto.PaymentInfoResponse;
import aplus.insurancesystem.domain.payment.dto.PaymentUpdateResponse;
import java.util.List;

public interface PaymentService {
    void createPayment(Contract contract);
    List<PaymentInfoResponse> getPaymentList(Long contractId);
    PaymentUpdateResponse updatePayment(Long paymentId);
}
