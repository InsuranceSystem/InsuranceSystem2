package aplus.insurancesystem2.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem2.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem2.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public CustomerInfoResponse getCustomerInfo(String userId) {
        return customerRepository.findById(userId)
                .map(CustomerInfoResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
