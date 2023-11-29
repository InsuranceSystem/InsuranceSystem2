package aplus.insurancesystem2.domain.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.contract.service.ContractService;
import aplus.insurancesystem2.domain.customer.dto.request.CustomerUpdateRequest;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerAllInfoResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerDetailResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem2.domain.customer.entity.TargetType;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.exception.CustomerNotFoundException;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerQueryService customerQueryService;
    private final FamilyHistoryService familyHistoryService;
    private final ContractService contractService;

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

    @Override
    public CustomerDetailResponse getCustomerDetail(Long customerId) {
        Customer customer = customerQueryService.getCustomer(customerId);
        return CustomerDetailResponse.of(
                customer,
                familyHistoryService.getFamilyHistories(customer),
                contractService.getContracts(customer)
        );
    }

    @Override
    @Transactional
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
    public void deleteCustomer(Long customerId) {
        Customer customer = customerQueryService.getCustomer(customerId);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerAllInfoResponse getCustomerAllInfo(Long customerId) {
        return customerRepository.findById(customerId)
                .map(CustomerAllInfoResponse::of)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<CustomerAllInfoResponse> getContractMaintenanceCustomers(TargetType targetType) {
        return targetType.applyFunction(customerRepository)
                         .stream()
                         .map(CustomerAllInfoResponse::of)
                         .collect(Collectors.toList());
    }
}
