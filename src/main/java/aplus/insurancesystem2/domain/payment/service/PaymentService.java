package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import java.util.List;

public interface PaymentService {
    boolean add(String paymentInfo);
    boolean delete();
    List<Payment> getAll() throws Exception;
    boolean update(Payment updatedPayment);
    List<Payment> get(String customerId, String selectedInsuranceId);
    List<Payment> getByCustomerId(String customerId);
    List<String> getUnpaidCustomerId();
    List<String> getStatus(String customerId, String insuranceId);
    boolean updateWhetherPayment(String customerId, String insuranceId);
}
