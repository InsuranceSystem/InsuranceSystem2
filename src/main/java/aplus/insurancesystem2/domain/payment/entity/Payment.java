package aplus.insurancesystem2.domain.payment.entity;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import java.time.LocalDate;

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
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
import lombok.Setter;

/**
 * CREATE TABLE Payment (
 *   id BIGINT AUTO_INCREMENT PRIMARY KEY,
 *   customerID BIGINT,
 *   insuranceID BIGINT,
 *   dateOfPayment DATE,
 *   whetherPayment BOOLEAN,
 *   FOREIGN KEY (customerID) REFERENCES Customer(customerID),
 *   FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID)
 * );
 */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID")
    private Insurance insurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Contract contract;

    private LocalDate dateOfPayment;
    private Boolean whetherPayment;

    public Payment(Customer customer, Insurance insurance, Contract contract,
                   LocalDate dateOfPayment, Boolean whetherPayment) {
        this.customer = customer;
        this.insurance = insurance;
        this.contract = contract;
        this.dateOfPayment = dateOfPayment;
        this.whetherPayment = whetherPayment;
    }
}
