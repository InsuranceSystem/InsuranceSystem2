package aplus.insurancesystem2.domain.customer.service;

import java.util.List;

import aplus.insurancesystem2.domain.customer.dto.request.CustomerUpdateRequest;
import aplus.insurancesystem2.domain.customer.dto.request.JoinRequest;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerAllInfoResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerDetailResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem2.domain.customer.entity.TargetType;

public interface CustomerService {
    CustomerInfoResponse getCustomerInfo(Long userId);

    CustomerIdResponse validateCustomer(String customerName, String pnumber);

    CustomerDetailResponse getCustomerDetail(Long customerId);

    void updateCustomer(Long customerId, CustomerUpdateRequest request);

    void deleteCustomer(Long customerId);

    CustomerAllInfoResponse getCustomerAllInfo(Long customerId);

    List<CustomerAllInfoResponse> getContractMaintenanceCustomers(TargetType targetType);

    void join(JoinRequest request);
}
