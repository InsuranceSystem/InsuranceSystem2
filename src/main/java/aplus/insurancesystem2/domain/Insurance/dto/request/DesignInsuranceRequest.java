package aplus.insurancesystem2.domain.Insurance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "새 보험 설계 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DesignInsuranceRequest {

    private final String insuranceName;
    private final String type;
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
