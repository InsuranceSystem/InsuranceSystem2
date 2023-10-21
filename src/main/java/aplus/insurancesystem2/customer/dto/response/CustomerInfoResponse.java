package aplus.insurancesystem2.customer.dto.response;

import aplus.insurancesystem2.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CustomerInfoResponse {

    private final String id;
    private final String name;
    private final String birth;
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
