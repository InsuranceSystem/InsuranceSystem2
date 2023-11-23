package aplus.insurancesystem2.domain.customer.dto.response;

import java.util.List;

import aplus.insurancesystem2.domain.contract.dto.ContractInfoResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "고객 상세 정보 Response")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDetailResponse {

    @Schema(description = "고객 핸드폰 번호")
    private final String customerPnumber;
    @Schema(description = "고객 주소")
    private final String customerAddress;
    @Schema(description = "고객 직업")
    private final String customerJob;
    @Schema(description = "가족력 정보 List")
    private final List<FamilyHistoryInfoResponse> familyHistoryInfoResponses;
    @Schema(description = "계약 정보 List")
    private final List<ContractInfoResponse> contractInfoResponses;

    public static CustomerDetailResponse of(Customer customer, List<FamilyHistory> familyHistories,
                                            List<Contract> contracts) {
        return new CustomerDetailResponse(
                customer.getPnumber(),
                customer.getAddress(),
                customer.getJob(),
                familyHistories.stream()
                               .map(FamilyHistoryInfoResponse::of)
                               .toList(),
                contracts.stream()
                           .map(contract -> ContractInfoResponse.of(
                                   contract.getInsurance().getInsuranceName(), contract))
                           .toList());
    }
}
