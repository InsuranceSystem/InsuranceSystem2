package aplus.insurancesystem.domain.contract.dto.response;

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
    private Long id;
    private String insuranceName;
    private String insuranceType;
    private Integer premium;
    private String paymentPeriod;
    private Boolean maturity;
    private Boolean resurrection;
    private Boolean cancellation;

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
