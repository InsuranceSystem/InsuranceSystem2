package aplus.insurancesystem2.Insurance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Insurance {

    @Id
    @Column(name = "insuranceID")
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
