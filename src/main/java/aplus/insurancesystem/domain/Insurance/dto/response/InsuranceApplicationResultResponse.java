package aplus.insurancesystem.domain.Insurance.dto.response;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplicationState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 신청 결과 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceApplicationResultResponse {

    @Schema(description = "보험 가입 신청 상태(PROCESSING, APPROVAL, REJECT)")
    private final InsuranceApplicationState state;
    private final String reasonOfApproval;
    private final String paymentPeriod;
    private final Integer premium;

    public static InsuranceApplicationResultResponse of(InsuranceApplication insuranceApplication) {
        return new InsuranceApplicationResultResponse(
                insuranceApplication.getState(),
                insuranceApplication.getReasonOfApproval(),
                insuranceApplication.getPaymentPeriod(),
                insuranceApplication.getPremium()
        );
    }
}
