package aplus.insurancesystem.domain.Insurance.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplicationState;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "내 보험신청 내역 Response")
@Builder(access = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MyInsuranceApplicationResponse {

    private final Long insuranceApplicationID;
    private final Long insuranceID;
    private final String insuranceName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate createdAt;
    private final String insurancePeriod;
    private final PaymentCycle paymentCycle;
    private final String paymentPeriod;
    private final String subscriptionFilePath;
    private final Integer premium;
    private final Integer maxCompensation;
    @Schema(description = "보험 가입 신청 상태(PROCESSING, APPROVAL, REJECT)")
    private final InsuranceApplicationState state;
    private final String reasonOfApproval;

    public static MyInsuranceApplicationResponse of(InsuranceApplication insuranceApplication) {
        return MyInsuranceApplicationResponse.builder()
                .insuranceID(insuranceApplication.getInsurance().getId())
                .insuranceName(insuranceApplication.getInsurance().getInsuranceName())
                .insuranceApplicationID(insuranceApplication.getId())
                .createdAt(insuranceApplication.getCreatedAt())
                .insurancePeriod(insuranceApplication.getInsurancePeriod())
                .paymentCycle(insuranceApplication.getPaymentCycle())
                .paymentPeriod(insuranceApplication.getPaymentPeriod())
                .subscriptionFilePath(insuranceApplication.getSubscriptionFilePath())
                .premium(insuranceApplication.getPremium())
                .maxCompensation(insuranceApplication.getMaxCompensation())
                .state(insuranceApplication.getState())
                .reasonOfApproval(insuranceApplication.getReasonOfApproval())
                .build();
    }
}
