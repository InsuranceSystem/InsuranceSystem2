package aplus.insurancesystem2.domain.security.service;

import aplus.insurancesystem2.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.entity.customer.EGender;
import aplus.insurancesystem2.domain.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import aplus.insurancesystem2.domain.customer.repository.FamilyHistoryRepository;
import aplus.insurancesystem2.domain.customer.entity.customer.Role;
import aplus.insurancesystem2.domain.security.dto.request.AuthorityRequest;
import aplus.insurancesystem2.domain.security.dto.request.JoinRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final FamilyHistoryRepository familyHistoryRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean verifyId(String username) {
        return !customerRepository.existsByCustomerId(username);
    }

    @Transactional
    public boolean join(JoinRequest request) {
        try {
            String username = request.getUsername();
            String encryptedPassword = passwordEncoder.encode(request.getPassword());
            EGender eGender = EGender.find(request.getGender());
            Customer joinCustomer = Customer.builder().customerId(username)
                                                .address(request.getAddress())
                                                .customerName(request.getName())
                                                .job(request.getJob())
                                                .pnumber(request.getPhoneNumber())
                                                .birth(request.getBirth())
                                                .eGender(eGender)
                                                .password(encryptedPassword).build();
            customerRepository.save(joinCustomer);

            request.getFamilyHistoryList().forEach(familyHistory ->
                    familyHistoryRepository.save(FamilyHistory.builder().customer(joinCustomer)
                                                        .diseaseName(familyHistory.getDiseaseName())
                                                        .relationship(familyHistory.getRelationship()).build()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void changeAuthority(AuthorityRequest request) {
        Customer customer = customerRepository.findByCustomerId(request.getUsername())
                .orElseThrow(CustomerNotFoundException::new);
        customer.setRole(Role.find(request.getRoleName()));
    }
}
