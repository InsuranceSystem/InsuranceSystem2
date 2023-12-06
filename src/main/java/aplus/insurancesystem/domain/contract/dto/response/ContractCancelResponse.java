package aplus.insurancesystem.domain.contract.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "계약 해지 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractCancelResponse {
    @Schema(description = "이미 해지된 계약인지 여부")
    private final Boolean isAlreadyCancelled;

    public static ContractCancelResponse of(Boolean isAlreadyCancelled) {
        return new ContractCancelResponse(isAlreadyCancelled);
    }
}
