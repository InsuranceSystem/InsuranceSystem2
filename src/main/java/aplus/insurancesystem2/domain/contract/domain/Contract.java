package aplus.insurancesystem2.domain.contract.domain;

import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import aplus.insurancesystem2.domain.customer.domain.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract {

    @Id @GeneratedValue
    private Long id;
    private String insurancePeriod;
    private int premium;
    private String paymentCycle;
    private int maxCompensation;
    private LocalDate dateOfSubscription;
    private LocalDate dateOfMaturity;
    private boolean maturity;
    private boolean resurrection;
    private boolean cancellation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", updatable = false, insertable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID", updatable = false, insertable = false)
    private Insurance insurance;

    public void changeCancellation() {
        this.cancellation = !this.cancellation;
    }

    public void changeResurrection(boolean resurrection) {
        this.resurrection = resurrection;
    }

    public void changeMaturity(boolean maturity) {
        this.maturity = maturity;
    }
}
