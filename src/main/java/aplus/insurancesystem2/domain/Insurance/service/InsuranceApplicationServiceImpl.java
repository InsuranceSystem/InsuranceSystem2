package aplus.insurancesystem2.domain.Insurance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.request.CalculatePremiumRequest;
import aplus.insurancesystem2.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.MyInsuranceApplicationResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.SubscriptionFilePathResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.Insurance.exception.InsuranceApplicationNotFoundException;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceApplicationRepository;
import aplus.insurancesystem2.domain.contract.service.ContractService;
import aplus.insurancesystem2.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.service.CustomerQueryService;
import aplus.insurancesystem2.domain.customer.service.FamilyHistoryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceApplicationServiceImpl implements InsuranceApplicationService {

    private final InsuranceApplicationRepository insuranceApplicationRepository;
    private final InsuranceApplicationQueryService insuranceApplicationQueryService;
    private final CustomerQueryService customerQueryService;
    private final InsuranceQueryService insuranceQueryService;
    private final FamilyHistoryService familyHistoryService;
    private final ContractService contractService;

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

    @Override
    public InsuranceApplicationDetailResponse getInsuranceApplication(Long insuranceApplicationId) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        List<FamilyHistory> familyHistories =
                familyHistoryService.getFamilyHistories(insuranceApplication.getCustomer());
        return InsuranceApplicationDetailResponse.of(insuranceApplication, familyHistories);
    }

    @Override
    public SubscriptionFilePathResponse getSubscription(Long insuranceApplicationId) {
        return SubscriptionFilePathResponse.of(
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId)
        );
    }

    @Override
    public InsuranceApplicationInfoResponse getInsuranceApplicationInfo(Long insuranceApplicationId) {
        return insuranceApplicationRepository.findById(insuranceApplicationId)
                                             .map(InsuranceApplicationInfoResponse::of)
                                             .orElseThrow(InsuranceApplicationNotFoundException::new);
    }

    @Override
    @Transactional
    public void calculatePremium(CalculatePremiumRequest request) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(request.getInsuranceApplicationId());
        insuranceApplication.setReasonOfApproval(request.getReasonOfApproval());
        insuranceApplication.setPaymentPeriod(request.getPaymentPeriod());
        insuranceApplication.setPremium(request.getPremium());
    }

    @Override
    @Transactional
    public void approvalInsuranceApplication(Long insuranceApplicationId) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        insuranceApplication.setApproval(true);

        contractService.createContract(insuranceApplication);
    }

    @Override
    @Transactional
    public void rejectionInsuranceApplication(Long insuranceApplicationId, String reasonOfRejection) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        insuranceApplication.setReasonOfApproval(reasonOfRejection);
    }

    @Override
    public List<MyInsuranceApplicationResponse> getMyInsuranceApplicationList(Long customerId) {
        return insuranceApplicationRepository.findAllByCustomerId(customerId).stream()
                                             .map(MyInsuranceApplicationResponse::of)
                                             .collect(Collectors.toList());
    }

    @Override
    public InsuranceApplicationResultResponse getInsuranceApplicationResult(Long insuranceApplicationId) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        return InsuranceApplicationResultResponse.of(insuranceApplication);
    }
}
