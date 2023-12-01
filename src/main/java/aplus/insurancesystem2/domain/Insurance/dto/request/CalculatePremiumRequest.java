package aplus.insurancesystem2.domain.Insurance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험료 산정 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CalculatePremiumRequest {

    private final Long insuranceApplicationId;
    private final String reasonOfApproval;
    private final String paymentPeriod;
    private final Integer premium;
}
