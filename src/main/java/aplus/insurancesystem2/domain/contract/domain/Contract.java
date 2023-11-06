package aplus.insurancesystem2.domain.contract.domain;

import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import aplus.insurancesystem2.domain.customer.domain.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@IdClass(ContractId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contract {

    @Id @EqualsAndHashCode.Include
    private String customerID;
    @Id @EqualsAndHashCode.Include
    private String insuranceID;
    private String insurancePeriod;
    private int premium;
    private String paymentCycle;
    private int maxCompensation;
    private LocalDate dateOfSubscription;
    private LocalDate dateOfMaturity;
    private boolean maturity;
    private boolean resurrection;
    private boolean cancellation;
    private String stringDateOfSubscription;
    private String stringDateOfMaturity;
    public Payment m_Payment;

    //optional?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", updatable = false, insertable = false)
    private Customer customer;

    //optional?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID", updatable = false, insertable = false)
    private Insurance insurance;

    //matchCustomerId

}
