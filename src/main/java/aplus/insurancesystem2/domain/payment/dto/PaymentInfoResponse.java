package aplus.insurancesystem2.domain.payment.dto;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "납입 정보 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PaymentInfoResponse {
    @Schema(description = "납입 id")
    private Long id;
    private Integer premium;
    private Boolean whetherPayment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate stringDateOfPayment;

    public static PaymentInfoResponse of(Payment payment, Integer premium) {
        return new PaymentInfoResponse(
                payment.getId(),
                premium,
                payment.getWhetherPayment(),
                payment.getDateOfPayment());
    }
}
