package aplus.insurancesystem2.domain.payment.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import aplus.insurancesystem2.domain.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
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
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(updatedPayment.getCustomer().getId(), updatedPayment.getInsurance().getId())) {
                paymentRepository.delete(payment);
                paymentRepository.save(updatedPayment);
                return true;
            }
        }
        return false;
    }

    public List<Payment> retrieveCustomerInsurancePayment(String customerId, String insuranceId) {
        List<Payment> customerPayment = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(customerId, insuranceId)) {
                customerPayment.add(payment);
            }
        }
        return customerPayment;
    }

    public List<Payment> retrieveCustomerPayment(String customerId) {
        List<Payment> customerPayment = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.getCustomer().getId().equals(customerId)) {
                customerPayment.add(payment);
            }
        }
        return customerPayment;
    }

    public ArrayList<String> retrieveUnpaidCustomerId() {
        ArrayList<String> unPaidCustomerId = new ArrayList<>();
        HashSet<String> uniqueCustomerId = new HashSet<>();

        for (Payment payment : paymentRepository.findAll()) {
            if (payment.isWhetherPayment() == false) {
                uniqueCustomerId.add(payment.getCustomer().getId());
            }
        }

        unPaidCustomerId.addAll(uniqueCustomerId);
        return unPaidCustomerId;
    }

    public List<String> retrieveDateStatusById(String customerId, String insuranceId) {
        List<String> dateAndStatus = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(customerId, insuranceId)) {
                dateAndStatus.add(payment.getDateOfPayment() + " " + payment.isWhetherPayment());
            }
        }
        return dateAndStatus;
    }

    @Transactional
    public boolean updateWhetherPayment(String customerId, String insuranceId) {
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(customerId, insuranceId)) {
                boolean newWhetherPayment = !payment.isWhetherPayment();
                payment.changeWhetherPayment(newWhetherPayment);
                return true;
            }
        }
        return false;
    }
}
