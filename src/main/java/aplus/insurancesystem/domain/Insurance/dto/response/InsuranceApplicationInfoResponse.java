package aplus.insurancesystem.domain.Insurance.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 가입 신청 내역 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceApplicationInfoResponse {

    private final Long insuranceApplicationID;
    private final Long insuranceID;
    private final Long customerID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate createdAt;

    public static InsuranceApplicationInfoResponse of(InsuranceApplication insuranceApplication) {
        return new InsuranceApplicationInfoResponse(
                insuranceApplication.getId(),
                insuranceApplication.getInsurance().getId(),
                insuranceApplication.getCustomer().getId(),
                insuranceApplication.getCreatedAt()
        );
    }

}
