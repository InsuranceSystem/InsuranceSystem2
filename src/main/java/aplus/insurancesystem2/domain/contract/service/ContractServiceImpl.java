package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.contract.dto.ContractDetailResponse;
import aplus.insurancesystem2.domain.contract.dto.ContractListElement;
import aplus.insurancesystem2.domain.contract.dto.ContractListResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.contract.exception.ContractNotFoundException;
import aplus.insurancesystem2.domain.contract.repository.ContractRepository;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    @Transactional
    public void createContract(InsuranceApplication insuranceApplication) {
        Contract contract = Contract.builder()
                .customer(insuranceApplication.getCustomer())
                .insurance(insuranceApplication.getInsurance())
                .insurancePeriod(insuranceApplication.getInsurancePeriod())
                .premium(insuranceApplication.getPremium())
                .paymentCycle(insuranceApplication.getPaymentCycle())
                .maxCompensation(insuranceApplication.getMaxCompensation())
                .dateOfSubscription(insuranceApplication.getCreatedAt())
                .dateOfMaturity(LocalDate.parse("2099-12-31"))
                .paymentPeriod(insuranceApplication.getPaymentPeriod())
                .maturity(false)
                .resurrection(false)
                .cancellation(false)
                .build();
        contractRepository.save(contract);
    }

    @Override
    public List<Contract> getContracts(Customer customer) {
        return contractRepository.findAllByCustomer(customer);
    }

    @Override
    public ContractDetailResponse getContractDetail(String contractId) {
        return contractRepository.findById(Long.parseLong(contractId))
                .map(ContractDetailResponse::of)
                .orElseThrow(ContractNotFoundException::new);
    }

    @Override
    public ContractListResponse getAllContract(String customerId) {
        return ContractListResponse.of(contractRepository.findByCustomerId(Long.parseLong(customerId))
                .stream()
                .map(ContractListElement::of)
                .collect(Collectors.toList()));
    }
}
