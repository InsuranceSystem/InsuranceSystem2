package aplus.insurancesystem2.domain.customer.service;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;

public interface CustomerQueryService {
    Customer getCustomer(Long customerId);
}
