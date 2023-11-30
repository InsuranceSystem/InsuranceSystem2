package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.payment.dto.PaymentListElement;
import aplus.insurancesystem2.domain.payment.dto.PaymentListResponse;
import aplus.insurancesystem2.domain.payment.entity.Payment;
import aplus.insurancesystem2.domain.payment.repository.PaymentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentListResponse getPaymentList(String contractId) {

    }
}
