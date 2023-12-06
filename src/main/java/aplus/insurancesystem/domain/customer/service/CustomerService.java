package aplus.insurancesystem.domain.customer.service;

import java.util.List;

import aplus.insurancesystem.domain.customer.dto.request.CustomerUpdateRequest;
import aplus.insurancesystem.domain.customer.dto.request.JoinRequest;
import aplus.insurancesystem.domain.customer.dto.response.CustomerAllInfoResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerDetailResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem.domain.customer.entity.TargetType;

public interface CustomerService {
    CustomerInfoResponse getCustomerInfo(Long userId);

    CustomerIdResponse validateCustomer(String customerName, String pnumber);

    CustomerDetailResponse getCustomerDetail(Long customerId);

    void updateCustomer(Long customerId, CustomerUpdateRequest request);

    void deleteCustomer(Long customerId);

    CustomerAllInfoResponse getCustomerAllInfo(Long customerId);

    List<CustomerAllInfoResponse> getContractMaintenanceCustomers(TargetType targetType);

    void join(JoinRequest request);

    Boolean isAdmin(Long customerId);

    Boolean validateLoginId(String customerId);

    List<CustomerAllInfoResponse> getCustomerList();

    void setAdmin(Long customerId, boolean setAdmin);
}
