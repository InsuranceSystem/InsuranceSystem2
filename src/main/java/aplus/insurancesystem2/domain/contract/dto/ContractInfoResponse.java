package aplus.insurancesystem2.domain.contract.dto;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractInfoResponse {

    private String id;
    private String insuranceName;
    private String insuranceType;
    private String insurancePeriod;
    private Integer premium;
    private String paymentCycle;
    private String maxCompensation;
    private String dateOfSubscription;
    private String dateOfMaturity;
    private Boolean maturity;
    private Boolean resurrection;
    private Boolean cancellation;

    public static ContractInfoResponse of(Contract contract) {
        return new ContractInfoResponse(String.valueOf(contract.getId()),
                contract.getInsurance().getInsuranceName(),
                contract.getInsurance().getType(),
                contract.getInsurancePeriod(),
                contract.getPremium(),
                contract.getPaymentCycle(),
                String.valueOf(contract.getMaxCompensation()),
                String.valueOf(contract.getDateOfSubscription()),
                String.valueOf(contract.getDateOfMaturity()),
                contract.getMaturity(),
                contract.getResurrection(),
                contract.getCancellation());
    }

}
