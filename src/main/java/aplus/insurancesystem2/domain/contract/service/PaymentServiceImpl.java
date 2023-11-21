package aplus.insurancesystem2.domain.contract.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.contract.domain.Payment;
import aplus.insurancesystem2.domain.contract.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public boolean add(String paymentInfo) {
        Payment newPayment = paymentRepository.save(new Payment()); // 정보 안집어넣나?
        if(!isNull(newPayment))
            return true;
        return false;
    }

    public boolean delete() {
        return false;
    }

    public List<Payment> retrieve() throws Exception {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.size() == 0) {
            throw new Exception("payment 데이터가 없습니다.");
        }
        return payments;
    }

    @Transactional
    public boolean update(Payment updatedPayment) {
        List<Payment> payments = paymentRepository.findAll();
        for (Payment payment : payments) {
            if (payment.match(updatedPayment.getCustomer().getId(), updatedPayment.getInsurance().getId())) {
                paymentRepository.delete(payment);
                paymentRepository.save(updatedPayment);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Payment> retrieveCustomerInsurancePayment(String customerId, String selectedInsuranceId) {
        return null;
    }

    public ArrayList<Payment> retrieveCustomerPayment(String selectedCustomerId) {
        return null;
    }

    public ArrayList<String> retrieveUnpaidCustomerId() {
        return null;
    }

    public ArrayList<String> retrieveDateStatusById(String selectedCustomerId, String selectedInsuranceId) {
        return null;
    }

    @Transactional
    public boolean updateWhetherPayment(String customerId, String insuranceId) {
        return false;
    }
}
