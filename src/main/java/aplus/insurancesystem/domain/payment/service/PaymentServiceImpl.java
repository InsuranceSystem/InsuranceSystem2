package aplus.insurancesystem.domain.payment.service;

import aplus.insurancesystem.domain.payment.dto.PaymentUpdateResponse;
import aplus.insurancesystem.domain.payment.entity.Payment;
import aplus.insurancesystem.domain.payment.exception.PaymentNotFoundException;
import aplus.insurancesystem.domain.contract.service.ContractService;
import aplus.insurancesystem.domain.payment.dto.PaymentInfoResponse;
import aplus.insurancesystem.domain.payment.repository.PaymentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ContractService contractService;
    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentInfoResponse> getPaymentList(Long contractId) {
        Integer premium = contractService.getPremium(contractId);

        return paymentRepository.findByContractId(contractId).stream()
                .map(payment -> PaymentInfoResponse.of(payment, premium))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentUpdateResponse updatePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);
        if (payment.getWhetherPayment()) {
            return PaymentUpdateResponse.of(true);
        }
        payment.setWhetherPayment(true);
        return PaymentUpdateResponse.of(false);
    }
}
