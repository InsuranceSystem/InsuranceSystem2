package aplus.insurancesystem.domain.customer.dto.response;

import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Schema(description = "고객 id Response")
public class CustomerIdResponse {

    @Schema(description = "고객 id", requiredMode = RequiredMode.REQUIRED)
    private final Long id;

    public static CustomerIdResponse of(Customer customer) {
        return new CustomerIdResponse(customer.getId());
    }
}
