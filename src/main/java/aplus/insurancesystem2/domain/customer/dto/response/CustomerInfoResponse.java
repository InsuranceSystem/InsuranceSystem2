package aplus.insurancesystem2.domain.customer.dto.response;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Schema(description = "고객 정보 Response")
public class CustomerInfoResponse {

    @Schema(description = "고객 id", requiredMode = RequiredMode.REQUIRED)
    private final Long id;
    @Schema(description = "고객 이름", requiredMode = RequiredMode.REQUIRED)
    private final String name;
    @Schema(description = "고객  생일", requiredMode = RequiredMode.REQUIRED)
    private final String birth;
    @Schema(description = "고객 성별(남/여)", requiredMode = RequiredMode.REQUIRED)
    private final String gender;

    public static CustomerInfoResponse of(Customer customer) {
        return new CustomerInfoResponse(
                customer.getId(),
                customer.getCustomerName(),
                customer.getBirth(),
                customer.getEGender().getGenderStr()
        );
    }
}
