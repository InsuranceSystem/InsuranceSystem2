package aplus.insurancesystem2.domain.payment.service;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import java.util.List;

public interface PaymentService {
    boolean add(String paymentInfo);
    boolean delete();
    List<Payment> retrieve() throws Exception;
    boolean update(Payment updatedPayment);
    List<Payment> retrieveCustomerInsurancePayment(String customerId, String selectedInsuranceId);
    List<Payment> retrieveCustomerPayment(String customerId);
    List<String> retrieveUnpaidCustomerId();
    List<String> retrieveDateStatusById(String customerId, String insuranceId);
    boolean updateWhetherPayment(String customerId, String insuranceId);
}
