package aplus.insurancesystem.domain.contract.dto.response;

import aplus.insurancesystem.domain.contract.entity.Contract;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "계약 정보 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractInfoResponse {

    @Schema(description = "보험 이름")
    private final String insuranceName;
    @Schema(description = "만기 여부")
    private final Boolean isMaturity;
    @Schema(description = "해지 여부")
    private final Boolean isCancellation;
    @Schema(description = "부활 여부")
    private final Boolean isResurrection;

    public static ContractInfoResponse of(String insuranceName, Contract contract) {
        return new ContractInfoResponse(insuranceName, contract.getMaturity(),
                contract.getCancellation(), contract.getResurrection());
    }
}
