package aplus.insurancesystem2.domain.payment.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PaymentListResponse {

    private List<PaymentListResponseElement> paymentList;

    public static PaymentListResponse of(List<PaymentListResponseElement> paymentList) {
        return new PaymentListResponse(paymentList);
    }
}
