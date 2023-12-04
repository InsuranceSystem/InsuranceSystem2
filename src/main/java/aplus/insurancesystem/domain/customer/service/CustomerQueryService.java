package aplus.insurancesystem.domain.customer.service;

import aplus.insurancesystem.domain.customer.entity.customer.Customer;

public interface CustomerQueryService {
    Customer getCustomer(Long customerId);
}
