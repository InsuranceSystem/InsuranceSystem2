package aplus.insurancesystem2.domain.Insurance.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "insurance")
    private List<Guarantee> guaranteeList;

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

    public Insurance(String insuranceName, String type, int maxCompensation, String periodOfInsurance,
                     String paymentCycle, String paymentPeriod, String ageOfTarget,
                     int basicPremium, String rate, boolean distributionStatus, String insuranceClausePeriod,
                     String precaution, boolean authorization) {
        this.guaranteeList = new ArrayList<>();
        this.insuranceName = insuranceName;
        this.type = type;
        this.maxCompensation = maxCompensation;
        this.periodOfInsurance = periodOfInsurance;
        this.paymentCycle = paymentCycle;
        this.paymentPeriod = paymentPeriod;
        this.ageOfTarget = ageOfTarget;
        this.basicPremium = basicPremium;
        this.rate = rate;
        this.distributionStatus = distributionStatus;
        this.insuranceClausePeriod = insuranceClausePeriod;
        this.precaution = precaution;
        this.authorization = authorization;
    }
}
