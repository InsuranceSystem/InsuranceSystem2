package aplus.insurancesystem2.domain.security.service;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import aplus.insurancesystem2.domain.security.domain.Role;
import aplus.insurancesystem2.domain.security.dto.request.AuthorityRequest;
import aplus.insurancesystem2.domain.security.dto.request.JoinRequest;
import aplus.insurancesystem2.domain.security.dto.response.JoinResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JoinResponse join(JoinRequest request) {
        findByCustomerId(request.getCustomerId());
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        Customer newCustomer = new Customer(...);
        customerRepository.save(newCustomer);
        return new JoinResponse(...);
    }

    @Transactional
    public void changeAuthority(AuthorityRequest request) {
        Customer customer = findByCustomerId(request.getCustomerId());
        customer.setRole(Role.find(request.getRoleName()));
    }

    private Customer findByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
