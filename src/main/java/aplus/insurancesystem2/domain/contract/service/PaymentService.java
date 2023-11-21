package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.contract.domain.Payment;
import java.util.ArrayList;
import java.util.List;

public interface PaymentService {
    boolean add(String paymentInfo);
    boolean delete();
    List<Payment> retrieve() throws Exception;
    boolean update(Payment updatedPayment);
    List<Payment> retrieveCustomerInsurancePayment(String customerId, String selectedInsuranceId);
    List<Payment> retrieveCustomerPayment(String selectedCustomerId);
    ArrayList<String> retrieveUnpaidCustomerId();
    List<String> retrieveDateStatusById(String selectedCustomerId, String selectedInsuranceId);
    boolean updateWhetherPayment(String selectedCustomerId, String selectedInsuranceId);
}
