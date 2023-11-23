package aplus.insurancesystem2.domain.Insurance.dto.response;

import java.util.stream.Collectors;

import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Schema(description = "보험 상세 조회 Response")
public class InsuranceDetailResponse {

    @Schema(description = "보험 id", required = true)
    private Long id;
    private String insuranceName;
    private String type;
    private int maxCompensation;
    private String periodOfInsurance;
    private String paymentCycle;
    private String paymentPeriod;
    private String ageOfTarget;
    private int basicPremium;
    private String rate;
    private boolean distributionStatus;
    @Schema(description = "보장 내용 id 리스트(콤마로 구분)")
    private String termsIDList;
    private String insuranceClausePeriod;
    private String precaution;
    private boolean authorization;

    public static InsuranceDetailResponse of(Insurance insurance) {
        return new InsuranceDetailResponse(
                insurance.getId(),
                insurance.getInsuranceName(),
                insurance.getType(),
                insurance.getMaxCompensation(),
                insurance.getPeriodOfInsurance(),
                insurance.getPaymentCycle(),
                insurance.getPaymentPeriod(),
                insurance.getAgeOfTarget(),
                insurance.getBasicPremium(),
                insurance.getRate(),
                insurance.isDistributionStatus(),
                insurance.getGuaranteeList().stream()
                         .map(guarantee -> guarantee.getTerms().getTermsID().toString())
                         .collect(Collectors.joining(", ")),
                insurance.getInsuranceClausePeriod(),
                insurance.getPrecaution(),
                insurance.isAuthorization()
        );
    }
}
