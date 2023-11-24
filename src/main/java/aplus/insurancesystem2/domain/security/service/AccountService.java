package aplus.insurancesystem2.domain.security.service;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import aplus.insurancesystem2.domain.security.domain.Role;
import aplus.insurancesystem2.domain.security.dto.AuthorizationRequest;
import aplus.insurancesystem2.domain.security.dto.JoinRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;

    @Transactional
    public boolean join(JoinRequest request) {
        Customer findCustomer = customerRepository.findByCustomerId(request.getCustomerId()).orElse(null);
        if (findCustomer != null) {
            return false; // 이미 존재하는 id
        }
        // 비밀번호 암호화해서 저장 (SecurityConfig -> passwordEncoder Bean 이용)
        Customer newCustomer = new Customer(...);
        customerRepository.save(newCustomer);
        return true; // 정상 동작
    }

    @Transactional
    public boolean changeAuthorization(AuthorizationRequest request) {
        Customer findCustomer = customerRepository.findByCustomerId(request.getCustomerId()).orElse(null);
        if (findCustomer == null) {
            return false; // account 존재 X
        }
        if (!Role.contains(request.getRoleName())) {
            return false; // 권한 존재 X
        }
        findCustomer.setRole(Role.find(request.getRoleName()));
        return true; // 정상 동작
    }
}
