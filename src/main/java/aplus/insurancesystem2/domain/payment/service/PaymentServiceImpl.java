package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.contract.service.ContractService;
import aplus.insurancesystem2.domain.payment.dto.PaymentInfoResponse;
import aplus.insurancesystem2.domain.payment.repository.PaymentRepository;
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
}
