package aplus.insurancesystem2.domain.payment.dto;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PaymentListElement {
    private String id;
    private String premium;
    private Boolean whetherPayment;
    private String StringDateOfPayment;

    public static PaymentListElement of(Payment payment) {
        return new PaymentListElement(String.valueOf(payment.getId()),
                                        String.valueOf(payment.getInsurance().getBasicPremium()),
                                        payment.getWhetherPayment(),
                                        String.valueOf(payment.getDateOfPayment()));
    }
}
