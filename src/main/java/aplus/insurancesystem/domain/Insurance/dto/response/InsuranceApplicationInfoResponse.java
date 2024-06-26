package aplus.insurancesystem.domain.Insurance.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplicationState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 가입 신청 내역 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceApplicationInfoResponse {

    private final Long insuranceApplicationID;
    private final Long insuranceID;
    private final String insuranceName;
    private final Long customerID;
    private final String customerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate createdAt;
    @Schema(description = "보험 가입 신청 상태(PROCESSING, APPROVAL, REJECT)")
    private final InsuranceApplicationState state;

    public static InsuranceApplicationInfoResponse of(InsuranceApplication insuranceApplication) {
        return new InsuranceApplicationInfoResponse(
                insuranceApplication.getId(),
                insuranceApplication.getInsurance().getId(),
                insuranceApplication.getInsurance().getInsuranceName(),
                insuranceApplication.getCustomer().getId(),
                insuranceApplication.getCustomer().getCustomerName(),
                insuranceApplication.getCreatedAt(),
                insuranceApplication.getState()
        );
    }

}
