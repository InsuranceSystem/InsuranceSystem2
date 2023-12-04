package aplus.insurancesystem.domain.Insurance.entity.insurauceApplication;

import java.time.LocalDate;

import aplus.insurancesystem.domain.Insurance.entity.Insurance;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private PaymentCycle paymentCycle;
    private String paymentPeriod;
    private String subscriptionFilePath;
    private Integer premium;
    private Integer maxCompensation;
    @Enumerated(EnumType.STRING)
    private InsuranceApplicationState state;
    private String reasonOfApproval;

    @Builder
    public InsuranceApplication(Insurance insurance, Customer customer, LocalDate createdAt,
                                String insurancePeriod, String paymentPeriod,
                                PaymentCycle paymentCycle, String subscriptionFilePath, Integer premium,
                                Integer maxCompensation, InsuranceApplicationState state, String reasonOfApproval) {
        this.insurance = insurance;
        this.customer = customer;
        this.createdAt = createdAt;
        this.insurancePeriod = insurancePeriod;
        this.paymentPeriod = paymentPeriod;
        this.paymentCycle = paymentCycle;
        this.subscriptionFilePath = subscriptionFilePath;
        this.premium = premium;
        this.maxCompensation = maxCompensation;
        this.state = state;
        this.reasonOfApproval = reasonOfApproval;
    }
}
