package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.contract.service.ContractService;
import aplus.insurancesystem2.domain.payment.dto.PaymentListResponseElement;
import aplus.insurancesystem2.domain.payment.dto.PaymentListResponse;
import aplus.insurancesystem2.domain.payment.entity.Payment;
import aplus.insurancesystem2.domain.payment.repository.PaymentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ContractService contractService;
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentListResponse getPaymentList(String contractId) {
        List<Payment> payments = paymentRepository.findByContractId(Long.parseLong(contractId));
        Integer premium = contractService.getPremium(contractId);

        List<PaymentListResponseElement> paymentListResponseElements = new ArrayList<>();

        for (Payment payment : payments) {
            paymentListResponseElements.add(PaymentListResponseElement.of(payment, premium));
        }

        return PaymentListResponse.of(paymentListResponseElements);
    }
}
