package aplus.insurancesystem2.domain.payment.controller;

import aplus.insurancesystem2.domain.payment.entity.Payment;
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

    @PostMapping("/create/payment")
    public boolean add(String paymentInfo) {
        return paymentService.add(paymentInfo);
    }

    @PostMapping("/delete")
    public boolean delete() {
        return paymentService.delete();
    }

    @GetMapping("/get/payment/all")
    public List<Payment> getAll() throws Exception {
        return paymentService.getAll();
    }

    @GetMapping("/get/customer-insurance")
    public List<Payment> get(@RequestParam Long customerId, @RequestParam Long insuranceId) {
        return paymentService.get(customerId, insuranceId);
    }

    @GetMapping("/get/payment/customer")
    public List<Payment> get(@RequestParam String customerId) {
        return paymentService.getByCustomerId(customerId);
    }

    @GetMapping("/date/get")
    public List<String> getDateStatus(@RequestParam Long customerId, @RequestParam Long insuranceId) {
        return paymentService.getStatus(customerId, insuranceId);
    }

    @GetMapping("/unpaid/get")
    public List<Long> getUnpaidCustomerId() {
        return paymentService.getUnpaidCustomerId();
    }

    @PostMapping("/update")
    public boolean update(Payment newPayment) {
        return paymentService.update(newPayment);
    }

    @PostMapping("/whether-payment/update")
    public boolean updateWhetherPayment(Long customerId, Long insuranceId) {
        return paymentService.updateWhetherPayment(customerId, insuranceId);
    }
}
