package aplus.insurancesystem.domain.Insurance.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;
import aplus.insurancesystem.domain.customer.dto.response.FamilyHistoryInfoResponse;
import aplus.insurancesystem.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem.domain.customer.entity.customer.EGender;
import aplus.insurancesystem.domain.customer.entity.customer.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 가입 신청 상세내역 Response")
@Builder(access = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InsuranceApplicationDetailResponse {

    private final String insuranceType;
    private final String insuranceName;
    @Schema(description = "기본 보험료")
    private final Integer insuranceBasicPremium;
    @Schema(description = "insuranceApplication.createAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDate insuranceApplicationDate;
    private final String customerName;
    private final String customerBirth;
    private final EGender customerGender;
    private final String customerAddress;
    private final String customerPhoneNumber;
    private final Job customerJob;
    private final List<FamilyHistoryInfoResponse> familyHistories;
    private final String insurancePeriod;
    private final PaymentCycle paymentCycle;

    public static InsuranceApplicationDetailResponse of(InsuranceApplication insuranceApplication,
                                                        List<FamilyHistory> familyHistories) {
        return InsuranceApplicationDetailResponse.builder()
                .insuranceType(insuranceApplication.getInsurance().getType())
                .insuranceName(insuranceApplication.getInsurance().getInsuranceName())
                .insuranceBasicPremium(insuranceApplication.getInsurance().getBasicPremium())
                .insuranceApplicationDate(insuranceApplication.getCreatedAt())
                .customerName(insuranceApplication.getCustomer().getCustomerName())
                .customerBirth(insuranceApplication.getCustomer().getBirth())
                .customerGender(insuranceApplication.getCustomer().getEGender())
                .customerAddress(insuranceApplication.getCustomer().getAddress())
                .customerPhoneNumber(insuranceApplication.getCustomer().getPnumber())
                .customerJob(insuranceApplication.getCustomer().getJob())
                .familyHistories(familyHistories.stream()
                                                .map(FamilyHistoryInfoResponse::of)
                                                .collect(Collectors.toList()))
                .insurancePeriod(insuranceApplication.getInsurancePeriod())
                .paymentCycle(insuranceApplication.getPaymentCycle())
                .build();
    }
}
