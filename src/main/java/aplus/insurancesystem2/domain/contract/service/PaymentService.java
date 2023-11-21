package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.contract.domain.Payment;
import java.util.ArrayList;
import java.util.List;

public interface PaymentService {
    boolean add(String paymentInfo);
    boolean delete();
    List<Payment> retrieve() throws Exception;
    boolean update(Payment updatedPayment);
    ArrayList<Payment> retrieveCustomerInsurancePayment(String customerId, String selectedInsuranceId);
    ArrayList<Payment> retrieveCustomerPayment(String selectedCustomerId);
    ArrayList<String> retrieveUnpaidCustomerId();
    ArrayList<String> retrieveDateStatusById(String selectedCustomerId, String selectedInsuranceId);
    boolean updateWhetherPayment(String selectedCustomerId, String selectedInsuranceId);
}
