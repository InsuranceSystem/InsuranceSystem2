package aplus.insurancesystem.domain.contract.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "계약 해지 정보 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractCancelContentResponse {
    private final String insuranceName;
    private final String customerName;
    private final String totalPremiumPaid;
    private final String refundAmount;

    public static ContractCancelContentResponse of(String insuranceName, String customerName,
                                            String totalPremiumPaid, String refundAmount) {
        return new ContractCancelContentResponse(insuranceName, customerName, totalPremiumPaid, refundAmount);
    }
}
