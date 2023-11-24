package aplus.insurancesystem2.domain.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem2.domain.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public CustomerInfoResponse getCustomerInfo(Long userId) {
        return customerRepository.findById(userId)
                .map(CustomerInfoResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerIdResponse validateCustomer(String name, String phone) {
        return customerRepository.findByCustomerNameAndPnumber(name, phone)
                .map(CustomerIdResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
