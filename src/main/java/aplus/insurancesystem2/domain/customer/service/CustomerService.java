package aplus.insurancesystem2.domain.customer.service;

import aplus.insurancesystem2.domain.customer.dto.request.CustomerUpdateRequest;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerAllInfoResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerDetailResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerInfoResponse;

public interface CustomerService {
    CustomerInfoResponse getCustomerInfo(Long userId);

    CustomerIdResponse validateCustomer(String customerName, String pnumber);

    CustomerDetailResponse getCustomerDetail(Long customerId);

    void updateCustomer(Long customerId, CustomerUpdateRequest request);

    void deleteCustomer(Long customerId);

    CustomerAllInfoResponse getCustomerAllInfo(Long customerId);
}
