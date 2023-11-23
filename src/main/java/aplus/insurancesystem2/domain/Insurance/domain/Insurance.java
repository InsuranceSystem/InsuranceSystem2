package aplus.insurancesystem2.domain.Insurance.domain;

import aplus.insurancesystem2.domain.Insurance.dto.request.insuranceCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insuranceID")
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
    private String insuranceClausePeriod;
    private String precaution;
    private boolean authorization;

    public Insurance(insuranceCreateRequest dto){
        this.id = dto.getId();
        this.insuranceName = dto.getInsuranceName();
        this.insuranceClausePeriod = dto.getInsuranceClausePeriod();
        this.periodOfInsurance = dto.getPeriodOfInsurance();
        this.ageOfTarget = dto.getAgeOfTarget();
        this.basicPremium = dto.getBasicPremium();
        this.distributionStatus = dto.isDistributionStatus();
        this.maxCompensation = dto.getMaxCompensation();
        this.paymentCycle = dto.getPaymentCycle();
        this.paymentPeriod = dto.getPaymentPeriod();
        this.precaution = dto.getPrecaution();
        this.rate = dto.getRate();
        this.type = dto.getType();
        this.authorization = dto.isAuthorization();
    }
}
