package aplus.insurancesystem2.domain.contract.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@IdClass(ContractId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {

    @Id @EqualsAndHashCode.Include
    private String customerID;
    @Id @EqualsAndHashCode.Include
    private String insuranceID;
    private LocalDate dateOfPayment;
    private boolean whetherPayment; // 미납 여부

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public void setDateOfPayment(LocalDate date) {
        this.dateOfPayment = date;
    }

    public void setWhetherPayment(boolean whetherPayment) {
        this.whetherPayment = whetherPayment;
    }

}
