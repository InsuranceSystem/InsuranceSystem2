package aplus.insurancesystem2.domain.payment.dto;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PaymentListResponseElement {
    private String id;
    private String premium;
    private Boolean whetherPayment;
    private String StringDateOfPayment;

    public static PaymentListResponseElement of(Payment payment, Integer premium) {
        return new PaymentListResponseElement(String.valueOf(payment.getId()),
                                        String.valueOf(premium),
                                        payment.getWhetherPayment(),
                                        String.valueOf(payment.getDateOfPayment()));
    }
}
