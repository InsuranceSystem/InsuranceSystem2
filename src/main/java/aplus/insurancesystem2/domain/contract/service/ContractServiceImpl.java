package aplus.insurancesystem2.domain.contract.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.contract.repository.ContractRepository;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public List<Contract> getContracts(Customer customer) {
        return contractRepository.findAllByCustomer(customer);
    }

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
}
