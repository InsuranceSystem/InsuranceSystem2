package aplus.insurancesystem2.domain.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "납입 요청 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PaymentUpdateResponse {
    @Schema(description = "이전에 납입이 완료되었는지 여부")
    boolean isExistedPayment;

    public static PaymentUpdateResponse of(Boolean isExistedPayment) {
        return new PaymentUpdateResponse(isExistedPayment);
    }
}
