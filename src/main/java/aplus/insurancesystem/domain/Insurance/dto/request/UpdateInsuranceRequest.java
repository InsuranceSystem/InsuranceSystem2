package aplus.insurancesystem.domain.Insurance.dto.request;

import aplus.insurancesystem.domain.Insurance.entity.insurance.InsuranceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 수정 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UpdateInsuranceRequest {

    private final String insuranceName;
    private final InsuranceType type;
    private final int maxCompensation;
    private final String periodOfInsurance;
    private final String ageOfTarget;
    private final int basicPremium;
    private final String rate;
    private final boolean distributionStatus;
    private final String insuranceClausePeriod;
    private final String precaution;
    @Schema(description = "보험 약관 id 리스트. ','로 구분해주세요.")
    private final String termsIdList;

}
