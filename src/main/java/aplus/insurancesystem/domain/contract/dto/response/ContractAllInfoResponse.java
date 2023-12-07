package aplus.insurancesystem.domain.contract.dto.response;

import aplus.insurancesystem.domain.Insurance.entity.insurance.InsuranceType;
import aplus.insurancesystem.domain.contract.entity.Contract;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "계약 전체 정보 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractAllInfoResponse {
    @Schema(description = "계약 id", requiredMode = RequiredMode.REQUIRED)
    private final Long id;
    private final String insuranceName;
    private final InsuranceType insuranceType;
    private final Integer premium;
    private final String paymentPeriod;
    private final Boolean maturity;
    private final Boolean resurrection;
    private final Boolean cancellation;

    public static ContractAllInfoResponse of(Contract contract) {
        return new ContractAllInfoResponse(
                contract.getId(),
                contract.getInsurance().getInsuranceName(),
                contract.getInsurance().getType(),
                contract.getPremium(),
                contract.getPaymentPeriod(),
                contract.getMaturity(),
                contract.getResurrection(),
                contract.getCancellation());
    }
}
