package aplus.insurancesystem.domain.Insurance.dto.response;

import java.util.stream.Collectors;

import aplus.insurancesystem.domain.Insurance.entity.Insurance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 상세 조회 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceDetailResponse {

    @Schema(description = "보험 id", required = true)
    private final Long id;
    private final String insuranceName;
    private final String type;
    private final int maxCompensation;
    private final String periodOfInsurance;
    private final String ageOfTarget;
    private final int basicPremium;
    private final boolean distributionStatus;
    @Schema(description = "보장 내용 id 리스트(콤마로 구분)")
    private final String termsIDList;
    private final String insuranceClausePeriod;
    private final String precaution;
    private final boolean authorization;

    public static InsuranceDetailResponse of(Insurance insurance) {
        return new InsuranceDetailResponse(
                insurance.getId(),
                insurance.getInsuranceName(),
                insurance.getType(),
                insurance.getMaxCompensation(),
                insurance.getPeriodOfInsurance(),
                insurance.getAgeOfTarget(),
                insurance.getBasicPremium(),
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
