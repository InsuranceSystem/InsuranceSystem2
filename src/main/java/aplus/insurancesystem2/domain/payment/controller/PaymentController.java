package aplus.insurancesystem2.domain.payment.controller;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.payment.dto.PaymentListResponse;
import aplus.insurancesystem2.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}/all")
    public ResponseEntity<SuccessResponse<PaymentListResponse>> getPaymentList(@PathVariable("id") String contractId) {
        return SuccessResponse.of(paymentService.getPaymentList(contractId))
                .asHttp(HttpStatus.OK);
    }
}
