package aplus.insurancesystem2.domain.payment.dto;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PaymentListResponseElement {
    @Schema(description = "납입 id")
    private Long id;
    private Integer premium;
    private Boolean whetherPayment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate StringDateOfPayment;

    public static PaymentListResponseElement of(Payment payment, Integer premium) {
        return new PaymentListResponseElement(
                payment.getId(),
                premium,
                payment.getWhetherPayment(),
                payment.getDateOfPayment());
    }
}
