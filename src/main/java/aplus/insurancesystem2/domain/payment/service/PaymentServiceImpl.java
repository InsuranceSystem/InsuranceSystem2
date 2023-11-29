package aplus.insurancesystem2.domain.payment.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import aplus.insurancesystem2.domain.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public boolean add(String paymentInfo) {
//        Payment newPayment = paymentRepository.save(new Payment());
//        if(!isNull(newPayment))
//            return true;
        return false;
    }

    public List<Payment> getAll() throws Exception {
        List<Payment> allPayments = paymentRepository.findAll();
        if (allPayments.size() == 0) {
            throw new Exception("payment 데이터가 없습니다.");
        }
        return allPayments;
    }

    public List<Payment> get(Long customerId, Long insuranceId) {
        List<Payment> customerPayment = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(customerId, insuranceId)) {
                customerPayment.add(payment);
            }
        }
        return customerPayment;
    }

    public List<Payment> getByCustomerId(String customerId) {
        List<Payment> customerPayment = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.getCustomer().getId().equals(customerId)) {
                customerPayment.add(payment);
            }
        }
        return customerPayment;
    }

    public List<Long> getUnpaidCustomerId() {
        List<Long> unPaidCustomerId = new ArrayList<>();
        Set<Long> uniqueCustomerId = new HashSet<>();

        for (Payment payment : paymentRepository.findAll()) {
            if (!payment.getWhetherPayment()) {
                uniqueCustomerId.add(payment.getCustomer().getId());
            }
        }

        unPaidCustomerId.addAll(uniqueCustomerId);
        return unPaidCustomerId;
    }

    public List<String> getStatus(Long customerId, Long insuranceId) {
        List<String> dateAndStatus = new ArrayList<>();
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(customerId, insuranceId)) {
                dateAndStatus.add(payment.getDateOfPayment() + " " + payment.getWhetherPayment());
            }
        }
        return dateAndStatus;
    }

    @Transactional
    public boolean update(Payment newPayment) {
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(newPayment.getCustomer().getId(), newPayment.getInsurance().getId())) {
                paymentRepository.delete(payment);
                paymentRepository.save(newPayment);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean updateWhetherPayment(Long customerId, Long insuranceId) {
        for (Payment payment : paymentRepository.findAll()) {
            if (payment.match(customerId, insuranceId)) {
                payment.setWhetherPayment(!payment.getWhetherPayment());
                return true;
            }
        }
        return false;
    }

    public boolean delete() {
        return false;
    }
}