package aplus.insurancesystem2.domain.contract.domain;

import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContractId implements Serializable {

    @Id
    private String customerID;
    @Id
    private String insuranceID;
}
