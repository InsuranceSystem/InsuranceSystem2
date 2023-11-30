package aplus.insurancesystem2.domain.contract.dto.response;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractListElement {
    private String id;
    private String insuranceName;
    private String insuranceType;
    private Integer premium;
    private String paymentPeriod;
    private Boolean maturity;
    private Boolean resurrection;
    private Boolean cancellation;

    public static ContractListElement of(Contract contract) {
        return new ContractListElement(String.valueOf(contract.getId()),
                contract.getInsurance().getInsuranceName(),
                contract.getInsurance().getType(),
                contract.getPremium(),
                contract.getPaymentPeriod(),
                contract.getMaturity(),
                contract.getResurrection(),
                contract.getCancellation());
    }
}
