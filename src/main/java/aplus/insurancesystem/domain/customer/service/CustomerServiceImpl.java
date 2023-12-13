package aplus.insurancesystem.domain.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem.common.cache.CacheConst;
import aplus.insurancesystem.domain.contract.service.ContractService;
import aplus.insurancesystem.domain.customer.dto.request.CustomerUpdateRequest;
import aplus.insurancesystem.domain.customer.dto.request.JoinRequest;
import aplus.insurancesystem.domain.customer.dto.response.CustomerAllInfoResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerDetailResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem.domain.customer.entity.TargetType;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import aplus.insurancesystem.domain.customer.entity.customer.Role;
import aplus.insurancesystem.domain.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerQueryService customerQueryService;
    private final FamilyHistoryService familyHistoryService;
    private final ContractService contractService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = CacheConst.CUSTOMER_INFO, key = "#customerId")
    public CustomerInfoResponse getCustomerInfo(Long customerId) {
        return customerRepository.findById(customerId)
                .map(CustomerInfoResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerIdResponse validateCustomer(String name, String phone) {
        return customerRepository.findByCustomerNameAndPnumber(name, phone)
                .map(CustomerIdResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerDetailResponse getCustomerDetail(Long customerId) {
        Customer customer = customerQueryService.getCustomer(customerId);
        return CustomerDetailResponse.of(
                customer,
                familyHistoryService.getFamilyHistoryList(customer),
                contractService.getContracts(customer)
        );
    }

    @Override
    @Transactional
    @CacheEvict(value = {CacheConst.CUSTOMER_INFO, CacheConst.CUSTOMER_ALL_INFO}, key = "#customerId")
    public void updateCustomer(Long customerId, CustomerUpdateRequest request) {
        Customer customer = customerQueryService.getCustomer(customerId);
        customer.setCustomerName(request.getCustomerName());
        customer.setEGender(request.getCustomerGender());
        customer.setBirth(request.getCustomerBirth());
        customer.setPnumber(request.getCustomerPnumber());
        customer.setAddress(request.getCustomerAddress());
        customer.setJob(request.getCustomerJob());
    }

    @Override
    @Transactional
    @CacheEvict(value = {CacheConst.CUSTOMER_INFO, CacheConst.CUSTOMER_ALL_INFO}, key = "#customerId")
    public void deleteCustomer(Long customerId) {
        Customer customer = customerQueryService.getCustomer(customerId);
        customerRepository.delete(customer);
    }

    @Override
    @Cacheable(value = CacheConst.CUSTOMER_ALL_INFO, key = "#customerId")
    public CustomerAllInfoResponse getCustomerAllInfo(Long customerId) {
        return customerRepository.findById(customerId)
                .map(CustomerAllInfoResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<CustomerAllInfoResponse> getContractMaintenanceCustomerList(TargetType targetType) {
        return targetType.applyFunction(customerRepository)
                         .stream()
                         .map(CustomerAllInfoResponse::of)
                         .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void join(JoinRequest request) {
        Customer joinCustomer = Customer.builder().loginId(request.getLoginId())
                                        .address(request.getAddress())
                                        .customerName(request.getName())
                                        .job(request.getJob())
                                        .pnumber(request.getPhoneNumber())
                                        .birth(request.getBirth())
                                        .eGender(request.getGender())
                                        .password(passwordEncoder.encode(request.getPassword()))
                                        .build();
        customerRepository.save(joinCustomer);

        request.getFamilyHistoryList()
                .forEach(familyHistory ->
                                 familyHistoryService.createFamilyHistory(joinCustomer, familyHistory));
    }

    @Override
    public Boolean isAdmin(Long customerId) {
        return customerQueryService.getCustomer(customerId)
                                   .getRole().equals(Role.ADMIN);
    }

    @Override
    public Boolean validateLoginId(String loginId) {
        return customerRepository.findByLoginId(loginId).isEmpty();
    }

    @Override
    public List<CustomerAllInfoResponse> getCustomerAllInfoList() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(CustomerAllInfoResponse::of)
                                 .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setAdmin(Long customerId, boolean setAdmin) {
        Customer customer = customerQueryService.getCustomer(customerId);
        customer.setRole(setAdmin ? Role.ADMIN : Role.CUSTOMER);
    }
}
