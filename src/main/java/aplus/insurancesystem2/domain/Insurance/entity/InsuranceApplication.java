package aplus.insurancesystem2.domain.Insurance.entity;

import java.time.LocalDate;

import aplus.insurancesystem2.domain.customer.entity.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InsuranceApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID")
    private Insurance insurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    private LocalDate createdAt;
    private String insurancePeriod;
    private String paymentCycle;
    private String subscriptionFilePath;
    private Integer premium;
    private Integer maxCompensation;
    private Boolean approval;
    private String reasonOfApproval;

    public InsuranceApplication(Insurance insurance, Customer customer, LocalDate createdAt,
                                String insurancePeriod,
                                String paymentCycle, String subscriptionFilePath, Integer premium,
                                Integer maxCompensation, Boolean approval, String reasonOfApproval) {
        this.insurance = insurance;
        this.customer = customer;
        this.createdAt = createdAt;
        this.insurancePeriod = insurancePeriod;
        this.paymentCycle = paymentCycle;
        this.subscriptionFilePath = subscriptionFilePath;
        this.premium = premium;
        this.maxCompensation = maxCompensation;
        this.approval = approval;
        this.reasonOfApproval = reasonOfApproval;
    }
}
