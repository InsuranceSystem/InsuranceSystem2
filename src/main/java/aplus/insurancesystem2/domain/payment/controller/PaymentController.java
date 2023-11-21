package aplus.insurancesystem2.domain.payment.controller;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import aplus.insurancesystem2.domain.payment.service.PaymentService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/payment/get")
    public List<Payment> get() throws Exception {
        return paymentService.retrieve();
    }

    boolean update(Payment updatedPayment);
    List<Payment> retrieveCustomerInsurancePayment(String customerId, String selectedInsuranceId);
    List<Payment> retrieveCustomerPayment(String selectedCustomerId);
    ArrayList<String> retrieveUnpaidCustomerId();
    List<String> retrieveDateStatusById(String selectedCustomerId, String selectedInsuranceId);
    boolean updateWhetherPayment(String selectedCustomerId, String selectedInsuranceId);


}
