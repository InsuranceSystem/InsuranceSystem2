package aplus.insurancesystem.domain.Insurance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.MyInsuranceApplicationResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.SubscriptionFilePathResponse;
import aplus.insurancesystem.domain.Insurance.entity.Insurance;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;
import aplus.insurancesystem.domain.Insurance.repository.InsuranceApplicationRepository;
import aplus.insurancesystem.domain.contract.service.ContractService;
import aplus.insurancesystem.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import aplus.insurancesystem.domain.customer.entity.customer.Job;
import aplus.insurancesystem.domain.customer.service.CustomerQueryService;
import aplus.insurancesystem.domain.customer.service.FamilyHistoryService;
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

        String paymentPeriod = calculatePeriod(request.getPaymentCycle(), insurance.getMaxCompensation(),
                                               insurance.getBasicPremium());
        float rate = calculateRate(customer, request.getPaymentCycle());
        int premium = Math.round(insurance.getBasicPremium() * rate);

        InsuranceApplication insuranceApplication = InsuranceApplication.builder()
                                                                        .insurance(insurance)
                                                                        .customer(customer)
                                                                        .createdAt(LocalDate.now())
                                                                        .insurancePeriod(insurance.getPeriodOfInsurance())
                                                                        .paymentCycle(request.getPaymentCycle())
                                                                        .paymentPeriod(paymentPeriod)
                                                                        .premium(premium)
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
    @Transactional
    public void approvalInsuranceApplication(Long insuranceApplicationId,
                                             String reasonOfApproval) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        insuranceApplication.setReasonOfApproval(reasonOfApproval);
        insuranceApplication.setApproval(true);

        contractService.createContract(insuranceApplication);
    }

    @Override
    @Transactional
    public void rejectionInsuranceApplication(Long insuranceApplicationId, String reasonOfRejection) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        insuranceApplication.setReasonOfApproval(reasonOfRejection);
        insuranceApplication.setApproval(false);
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

    private String calculatePeriod(PaymentCycle paymentCycle, int maxCompensation, int basicPremium) {
        int paymentPeriod = (int) Math.ceil((double) maxCompensation / basicPremium) * paymentCycle.getValue();
        return paymentPeriod + "개월";
    }

    private float calculateRate(Customer customer, PaymentCycle paymentCycle) {
        int age = LocalDate.parse(customer.getBirth()).until(LocalDate.now()).getYears();
        Job job = customer.getJob();
        float rate = 0;

        if (age < 20) {
            rate += 0.02;
        } else if (age < 50) {
            rate += 0.01;
        } else if (age < 70) {
            rate += 0.02;
        } else {
            rate += 0.03;
        }

        rate += job.getAdditionalRate();

        rate += paymentCycle.getAdditionalRate();

        return rate;
    }
}
