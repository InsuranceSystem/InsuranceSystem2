package aplus.insurancesystem2.domain.Insurance.dto.response;

import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Schema(description = "보험 상세 조회 Response")
public class InsuranceInfoResponse {
    private final Long id;
    private final String insuranceName;
    private final String type;
    private final int maxCompensation;
    private final String periodOfInsurance;
    private final String paymentCycle;
    private final String paymentPeriod;
    private final String ageOfTarget;
    private final int basicPremium;
    private final String rate;
    private final boolean distributionStatus;

    private final String TermsIDList;
    private final String insuranceClausePeriod;
    private final String precaution;
    private final boolean authorization;

    public static InsuranceInfoResponse of(Insurance insurance) {
        return new InsuranceInfoResponse(
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
                insurance.getTermsIDList(),
                insurance.getInsuranceClausePeriod(),
                insurance.getPrecaution(),
                insurance.isAuthorization()
        );
    }
}
