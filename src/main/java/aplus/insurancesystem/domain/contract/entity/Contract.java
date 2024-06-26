package aplus.insurancesystem.domain.contract.entity;

import java.time.LocalDate;

import aplus.insurancesystem.domain.Insurance.entity.insurance.Insurance;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;
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
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID")
    private Insurance insurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String insurancePeriod;
    private String paymentPeriod;
    private Integer premium;
    @Enumerated(EnumType.STRING)
    private PaymentCycle paymentCycle;
    private Integer maxCompensation;
    private LocalDate dateOfSubscription;
    private LocalDate dateOfMaturity;
    private Boolean maturity;
    private Boolean resurrection;
    private Boolean cancellation;

    @Builder
    public Contract(Insurance insurance, Customer customer, String insurancePeriod, Integer premium,
                    PaymentCycle paymentCycle, String paymentPeriod, Integer maxCompensation,
                    LocalDate dateOfSubscription, LocalDate dateOfMaturity, Boolean maturity,
                    Boolean resurrection, Boolean cancellation) {
        this.insurance = insurance;
        this.customer = customer;
        this.insurancePeriod = insurancePeriod;
        this.premium = premium;
        this.paymentCycle = paymentCycle;
        this.paymentPeriod = paymentPeriod;
        this.maxCompensation = maxCompensation;
        this.dateOfSubscription = dateOfSubscription;
        this.dateOfMaturity = dateOfMaturity;
        this.maturity = maturity;
        this.resurrection = resurrection;
        this.cancellation = cancellation;
    }
}
