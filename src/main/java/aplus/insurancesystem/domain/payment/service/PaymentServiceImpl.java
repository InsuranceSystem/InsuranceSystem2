package aplus.insurancesystem.domain.payment.service;

import aplus.insurancesystem.domain.contract.entity.Contract;
import aplus.insurancesystem.domain.contract.service.ContractQueryService;
import aplus.insurancesystem.domain.payment.dto.PaymentUpdateResponse;
import aplus.insurancesystem.domain.payment.entity.Payment;
import aplus.insurancesystem.domain.payment.exception.PaymentNotFoundException;
import aplus.insurancesystem.domain.payment.dto.PaymentInfoResponse;
import aplus.insurancesystem.domain.payment.repository.PaymentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ContractQueryService contractQueryService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void createPayment(Contract contract) {
        String paymentPeriod = contract.getPaymentPeriod();
        int paymentPeriodMonths = Integer.parseInt(paymentPeriod.substring(0, paymentPeriod.length() - 2));
        int paymentCycle = contract.getPaymentCycle().getValue();

        LocalDate paymentDate = contract.getDateOfSubscription();
        for (int i = 1; i <= paymentPeriodMonths / paymentCycle; i++) {
            Payment payment = Payment.builder()
                    .customer(contract.getCustomer())
                    .insurance(contract.getInsurance())
                    .contract(contract)
                    .whetherPayment(false)
                    .dateOfPayment(paymentDate)
                    .build();
            paymentRepository.save(payment);
            paymentDate = paymentDate.plusMonths(paymentCycle);
        }
    }

    @Override
    public List<PaymentInfoResponse> getPaymentList(Long contractId) {
        Integer premium = contractQueryService.getPremium(contractId);
        LocalDate today = LocalDate.now();

        return paymentRepository.findByContractId(contractId).stream()
                .filter(payment -> payment.getDateOfPayment().isEqual(today)
                        || payment.getDateOfPayment().isBefore(today))
                .map(payment -> PaymentInfoResponse.of(payment, premium))
                .collect(Collectors.toList());
    }

    public Double getTotalPremiumPaid(Long contractId) {
        return paymentRepository.findByContractId(contractId)
                .stream()
                .filter(Payment::getWhetherPayment)
                .mapToDouble(payment -> payment.getContract().getPremium())
                .sum();
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
