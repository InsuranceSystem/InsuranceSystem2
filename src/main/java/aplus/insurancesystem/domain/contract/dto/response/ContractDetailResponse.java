package aplus.insurancesystem.domain.contract.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import aplus.insurancesystem.domain.Insurance.entity.insurance.InsuranceType;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;
import aplus.insurancesystem.domain.contract.entity.Contract;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "계약 상세 정보 Response")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractDetailResponse {

    @Schema(description = "계약 id")
    private Long id;
    private String insuranceName;
    private InsuranceType insuranceType;
    private String insurancePeriod;
    private Integer premium;
    private PaymentCycle paymentCycle;
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
