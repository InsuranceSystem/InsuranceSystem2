package aplus.insurancesystem2.domain.contract.dto;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractInfo {
    private String id;
    private String insuranceName;
    private String insuranceType;
    private Integer premium;
    private String paymentPeriod;
    private Boolean maturity;
    private Boolean resurrection;
    private Boolean cancellation;

    public static ContractInfo of(Contract contract) {
        return new ContractInfo(String.valueOf(contract.getId()),
                contract.getInsurance().getInsuranceName(),
                contract.getInsurance().getType(),
                contract.getPremium(),
                contract.getPaymentPeriod(),
                contract.getMaturity(),
                contract.getResurrection(),
                contract.getCancellation());
    }
}
