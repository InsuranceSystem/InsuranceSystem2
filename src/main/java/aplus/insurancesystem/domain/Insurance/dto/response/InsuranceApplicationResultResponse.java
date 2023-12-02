package aplus.insurancesystem.domain.Insurance.dto.response;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 신청 결과 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceApplicationResultResponse {

    private final Boolean isApproved;
    private final String reasonOfApproval;
    private final String paymentPeriod;
    private final Integer premium;

    public static InsuranceApplicationResultResponse of(InsuranceApplication insuranceApplication) {
        return new InsuranceApplicationResultResponse(
                insuranceApplication.getApproval(),
                insuranceApplication.getReasonOfApproval(),
                insuranceApplication.getPaymentPeriod(),
                insuranceApplication.getPremium()
        );
    }
}
