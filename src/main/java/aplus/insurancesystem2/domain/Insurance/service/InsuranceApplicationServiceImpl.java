package aplus.insurancesystem2.domain.Insurance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceApplicationRepository;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.service.CustomerQueryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceApplicationServiceImpl implements InsuranceApplicationService {

    private final InsuranceApplicationRepository insuranceApplicationRepository;
    private final CustomerQueryService customerQueryService;
    private final InsuranceQueryService insuranceQueryService;

    @Override
    @Transactional
    public void applyInsurance(Long insuranceId, CreateInsuranceApplicationRequest request) {
        Insurance insurance = insuranceQueryService.getInsurance(insuranceId);
        Customer customer = customerQueryService.getCustomer(request.getCustomerId());

        InsuranceApplication insuranceApplication = InsuranceApplication.builder()
                                                                        .insurance(insurance)
                                                                        .customer(customer)
                                                                        .createdAt(LocalDate.now())
                                                                        .insurancePeriod(insurance.getPeriodOfInsurance())
                                                                        .paymentCycle(request.getPaymentCycle())
                                                                        .maxCompensation(insurance.getMaxCompensation())
                                                                        .subscriptionFilePath(request.getSubscriptionFilePath())
                                                                        .build();
        insuranceApplicationRepository.save(insuranceApplication);
    }

    @Override
    public List<InsuranceApplicationInfoResponse> getInsuranceApplicationList() {
        return insuranceApplicationRepository.findAll().stream()
                .map(InsuranceApplicationInfoResponse::of)
                .toList();
    }
}
