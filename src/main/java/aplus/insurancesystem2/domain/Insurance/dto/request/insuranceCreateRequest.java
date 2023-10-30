package aplus.insurancesystem2.domain.Insurance.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class insuranceCreateRequest {
    @NotBlank
    private String id;
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

    private String TermsIDList;
    private String insuranceClausePeriod;
    private String precaution;
    private boolean authorization;
}
