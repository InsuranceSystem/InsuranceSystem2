package aplus.insurancesystem.domain.customer.dto.response;

import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Schema(description = "고객 전체 정보 Response")
public class CustomerAllInfoResponse {

    private final Long id;
    private final String name;
    private final String birth;
    private final String gender;
    private final String pnumber;
    private final String address;
    private final String job;

    public static CustomerAllInfoResponse of (Customer customer) {
        return new CustomerAllInfoResponse(
                customer.getId(),
                customer.getCustomerName(),
                customer.getBirth(),
                customer.getEGender().getGenderStr(),
                customer.getPnumber(),
                customer.getAddress(),
                customer.getJob()
        );
    }
}
