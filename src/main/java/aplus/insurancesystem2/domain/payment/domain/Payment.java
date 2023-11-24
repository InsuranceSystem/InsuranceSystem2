package aplus.insurancesystem2.domain.payment.domain;

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
public class Payment {

    @Id @GeneratedValue
    private Long id;
    private LocalDate dateOfPayment;
    private boolean whetherPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", updatable = false, insertable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID", updatable = false, insertable = false)
    private Insurance insurance;

    public Payment() {
        this.whetherPayment = false;
    }

    public boolean match(Long customerId, Long insuranceId) {
        if (this.customer.getId() == customerId && this.insurance.getId() == insuranceId) {
            return true;
        }
        return false;
    }

    public void changeWhetherPayment() {
        this.whetherPayment = !this.whetherPayment;
    }
}
