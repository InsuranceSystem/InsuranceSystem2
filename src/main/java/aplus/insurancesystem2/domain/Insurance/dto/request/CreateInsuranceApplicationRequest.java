package aplus.insurancesystem2.domain.Insurance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 신청 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateInsuranceApplicationRequest {

    private final Long customerId;
    private final String insurancePeriod;
    private final String paymentCycle;
    private final String paymentPeriod;
    private final String subscriptionFilePath;

}
