package aplus.insurancesystem.domain.contract.service;

import aplus.insurancesystem.domain.contract.dto.response.ContractCancelContentResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractCancelResponse;
import aplus.insurancesystem.domain.payment.service.PaymentService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.contract.dto.response.ContractAllInfoResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractDetailResponse;
import aplus.insurancesystem.domain.contract.entity.Contract;
import aplus.insurancesystem.domain.contract.exception.ContractNotFoundException;
import aplus.insurancesystem.domain.contract.repository.ContractRepository;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public void createContract(InsuranceApplication insuranceApplication) {
        LocalDate dateOfSubscription = insuranceApplication.getCreatedAt();
        String insurancePeriod = insuranceApplication.getInsurancePeriod();
        LocalDate dateOfMaturity = LocalDate.of(2099, 12, 31);
        if (insurancePeriod.contains("년")) {
            int endIndex = insurancePeriod.indexOf("년");
            int extractedInsurancePeriod = Integer.parseInt(insurancePeriod.substring(0, endIndex));
            dateOfMaturity = dateOfSubscription.plusYears(extractedInsurancePeriod);
        }

        Contract contract = Contract.builder()
                .customer(insuranceApplication.getCustomer())
                .insurance(insuranceApplication.getInsurance())
                .insurancePeriod(insuranceApplication.getInsurancePeriod())
                .premium(insuranceApplication.getPremium())
                .paymentCycle(insuranceApplication.getPaymentCycle())
                .maxCompensation(insuranceApplication.getMaxCompensation())
                .dateOfSubscription(insuranceApplication.getCreatedAt())
                .dateOfMaturity(dateOfMaturity)
                .paymentPeriod(insuranceApplication.getPaymentPeriod())
                .maturity(false)
                .resurrection(false)
                .cancellation(false)
                .build();
        contractRepository.save(contract);
        paymentService.createPayment(contract);
    }

    @Override
    public List<Contract> getContracts(Customer customer) {
        return contractRepository.findAllByCustomer(customer);
    }

    @Override
    public ContractDetailResponse getContractDetail(Long contractId) {
        return contractRepository.findById(contractId)
                .map(ContractDetailResponse::of)
                .orElseThrow(ContractNotFoundException::new);
    }

    @Override
    public List<ContractAllInfoResponse> getContractList(Long customerId) {
        return contractRepository.findByCustomerId(customerId)
                .stream()
                .map(ContractAllInfoResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public ContractCancelContentResponse getCancelContent(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractNotFoundException());
        Double totalPremiumPaid = paymentService.getTotalPremiumPaid(contractId);
        Double refundAmount;
        if (contract.getMaturity()) {
            refundAmount = totalPremiumPaid * 0.8;
        }
        else {
            refundAmount = (totalPremiumPaid - (totalPremiumPaid * 0.6) + totalPremiumPaid * 0.3);
        }
        return ContractCancelContentResponse.of(contract.getInsurance().getInsuranceName(),
                contract.getCustomer().getCustomerName(),
                String.valueOf(totalPremiumPaid),
                String.valueOf(refundAmount));
    }

    @Override
    @Transactional
    public ContractCancelResponse cancel(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ContractNotFoundException());
        if (contract.getCancellation()) {
            return ContractCancelResponse.of(true);
        }
        contract.setCancellation(true);
        return ContractCancelResponse.of(false);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void updateMaturity() {
        contractRepository.findAll()
                .stream()
                .filter(contract -> contract.getDateOfMaturity().isEqual(LocalDate.now()))
                .forEach(contract -> contract.setMaturity(true));
    }
}
