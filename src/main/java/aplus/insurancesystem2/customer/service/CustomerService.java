package aplus.insurancesystem2.customer.service;

import aplus.insurancesystem2.customer.dto.response.CustomerInfoResponse;

public interface CustomerService {
    CustomerInfoResponse getCustomerInfo(String userId);
}
