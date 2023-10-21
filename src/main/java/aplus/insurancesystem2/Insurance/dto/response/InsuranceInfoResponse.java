package aplus.insurancesystem2.Insurance.dto.response;

import aplus.insurancesystem2.Insurance.domain.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceInfoResponse {
    private final String id;
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
