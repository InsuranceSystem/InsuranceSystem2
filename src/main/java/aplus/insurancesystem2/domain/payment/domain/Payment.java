package aplus.insurancesystem2.domain.payment.domain;

import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import aplus.insurancesystem2.domain.customer.domain.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
public class Payment {

    @Id @GeneratedValue
    private Long id;
    private LocalDate dateOfPayment;
    private boolean whetherPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", updatable = false, insertable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID", updatable = false, insertable = false)
    private Insurance insurance;

    public Payment() {
        this.whetherPayment = false;
    }

    public boolean match(String customerId, String insuranceId) {
        if (this.customer.getId().equals(customerId) && this.insurance.getId().equals(insuranceId)) {
            return true;
        }
        return false;
    }

    public void changeWhetherPayment(boolean whetherPayment) {
        this.whetherPayment = whetherPayment;
    }
}
