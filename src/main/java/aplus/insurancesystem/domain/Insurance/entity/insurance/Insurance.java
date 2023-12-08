package aplus.insurancesystem.domain.Insurance.entity.insurance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import aplus.insurancesystem.domain.Insurance.entity.Guarantee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deletedAt is null")
@SQLDelete(sql = "UPDATE Insurance SET deletedAt = current_timestamp WHERE insuranceID = ?")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insuranceID")
    private Long id;

    @OneToMany(mappedBy = "insurance")
    private List<Guarantee> guaranteeList;

    private String insuranceName;
    private InsuranceType type;
    private int maxCompensation;
    private String periodOfInsurance;
    private String ageOfTarget;
    private int basicPremium;
    private boolean distributionStatus;
    private String insuranceClausePeriod;
    private String precaution;
    private boolean authorization;
    private LocalDateTime deletedAt;

    @Builder
    public Insurance(String insuranceName, InsuranceType type, int maxCompensation, String periodOfInsurance,
                     String ageOfTarget, int basicPremium, boolean distributionStatus,
                     String insuranceClausePeriod, String precaution, boolean authorization) {
        this.guaranteeList = new ArrayList<>();
        this.insuranceName = insuranceName;
        this.type = type;
        this.maxCompensation = maxCompensation;
        this.periodOfInsurance = periodOfInsurance;
        this.ageOfTarget = ageOfTarget;
        this.basicPremium = basicPremium;
        this.distributionStatus = distributionStatus;
        this.insuranceClausePeriod = insuranceClausePeriod;
        this.precaution = precaution;
        this.authorization = authorization;
    }
}
