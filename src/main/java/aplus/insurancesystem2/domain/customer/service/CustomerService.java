package aplus.insurancesystem2.domain.customer.service;

import aplus.insurancesystem2.domain.customer.dto.response.CustomerInfoResponse;

public interface CustomerService {
    CustomerInfoResponse getCustomerInfo(Long userId);
}
