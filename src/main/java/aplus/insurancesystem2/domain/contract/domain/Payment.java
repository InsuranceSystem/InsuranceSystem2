package aplus.insurancesystem2.domain.contract.domain;

import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import aplus.insurancesystem2.domain.customer.domain.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id @GeneratedValue
    private Long id;
    private LocalDate dateOfPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", updatable = false, insertable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insuranceID", updatable = false, insertable = false)
    private Insurance insurance;
}
