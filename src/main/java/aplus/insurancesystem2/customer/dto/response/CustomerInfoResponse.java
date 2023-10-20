package aplus.insurancesystem2.customer.dto.response;

import aplus.insurancesystem2.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CustomerInfoResponse {

    private String id;
    private String name;
    private String birth;
    private String gender;

    public static CustomerInfoResponse of(Customer customer) {
        return new CustomerInfoResponse(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getBirth(),
                customer.getEGender().getGenderStr()
        );
    }
}
