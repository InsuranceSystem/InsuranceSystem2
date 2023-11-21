package aplus.insurancesystem2.domain.payment.controller;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import aplus.insurancesystem2.domain.payment.service.PaymentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/create")
    public boolean add(String paymentInfo) {
        return paymentService.add(paymentInfo);
    }

    @PostMapping("/payment/delete")
    public boolean delete() {
        return paymentService.delete();
    }

    @GetMapping("/payment/get/all")
    public List<Payment> getAll() throws Exception {
        return paymentService.retrieve();
    }

    @GetMapping("/payment/get")
    public List<Payment> get(@RequestParam String customerId, @RequestParam String insuranceId) {
        return paymentService.retrieveCustomerInsurancePayment(customerId, insuranceId);
    }

    @GetMapping("/payment/get")
    public List<Payment> get(@RequestParam String customerId) {
        return paymentService.retrieveCustomerPayment(customerId);
    }

    @GetMapping("/payment/date/get")
    public List<String> getDateStatus(@RequestParam String customerId, @RequestParam String insuranceId) {
        return paymentService.retrieveDateStatusById(customerId, insuranceId);
    }

    @GetMapping("/payment/unpaid/get")
    public List<String> getUnpaidCustomerId() {
        return paymentService.retrieveUnpaidCustomerId();
    }

    @PostMapping("/payment/update")
    public boolean update(Payment newPayment) {
        return paymentService.update(newPayment);
    }

    @PostMapping("/payment/whether-payment/update")
    public boolean updateWhetherPayment(String customerId, String insuranceId) {
        return paymentService.updateWhetherPayment(customerId, insuranceId);
    }
}
