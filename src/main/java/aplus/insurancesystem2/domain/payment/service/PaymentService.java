package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import java.util.List;

public interface PaymentService {
    boolean add(String paymentInfo);
    boolean delete();
    List<Payment> getAll() throws Exception;
    boolean update(Payment updatedPayment);
    List<Payment> get(Long customerId, Long selectedInsuranceId);
    List<Payment> getByCustomerId(String customerId);
    List<Long> getUnpaidCustomerId();
    List<String> getStatus(Long customerId, Long insuranceId);

    boolean updateWhetherPayment(Long customerId, Long insuranceId);
}
