package aplus.insurancesystem.domain.contract.dto.response;

import aplus.insurancesystem.domain.contract.entity.Contract;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "계약 상세 정보 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractDetailResponse {

    @Schema(description = "계약 id")
    private Long id;
    private String insuranceName;
    private String insuranceType;
    private String insurancePeriod;
    private Integer premium;
    private String paymentCycle;
    private String paymentPeriod;
    private Integer maxCompensation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfSubscription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfMaturity;

    public static ContractDetailResponse of(Contract contract) {
        return new ContractDetailResponse(
                contract.getId(),
                contract.getInsurance().getInsuranceName(),
                contract.getInsurance().getType(),
                contract.getInsurancePeriod(),
                contract.getPremium(),
                contract.getPaymentCycle(),
                contract.getPaymentPeriod(),
                contract.getMaxCompensation(),
                contract.getDateOfSubscription(),
                contract.getDateOfMaturity());
    }
}
