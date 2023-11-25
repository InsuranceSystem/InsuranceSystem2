package aplus.insurancesystem2.domain.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                                 .orElseThrow(CustomerNotFoundException::new);
    }
}
