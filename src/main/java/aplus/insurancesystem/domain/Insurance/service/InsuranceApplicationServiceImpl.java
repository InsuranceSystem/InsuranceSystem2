package aplus.insurancesystem.domain.Insurance.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import aplus.insurancesystem.common.service.FileService;
import aplus.insurancesystem.domain.Insurance.dto.request.ApplyInsuranceRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.MyInsuranceApplicationResponse;
import aplus.insurancesystem.domain.Insurance.entity.insurance.Insurance;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplicationState;
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
    private final FileService fileService;

    @Override
    @Transactional
    public void applyInsurance(Long insuranceId, ApplyInsuranceRequest request) {
        Insurance insurance = insuranceQueryService.getInsurance(insuranceId);
        Customer customer = customerQueryService.getCustomer(request.getCustomerId());

        String paymentPeriod = calculatePeriod(request.getPaymentCycle(), insurance.getMaxCompensation(),
                                               insurance.getBasicPremium());
        float rate = calculateRate(customer, request.getPaymentCycle());
        int premium = Math.round(insurance.getBasicPremium() * rate);

        MultipartFile subscriptionFile = request.getSubscriptionFile();
        String subscriptionFilePath = subscriptionFile.getOriginalFilename() + LocalDateTime.now();
        fileService.uploadFile(subscriptionFile, subscriptionFilePath);

        InsuranceApplication insuranceApplication = InsuranceApplication.builder()
                                                                        .insurance(insurance)
                                                                        .customer(customer)
                                                                        .createdAt(LocalDate.now())
                                                                        .insurancePeriod(insurance.getPeriodOfInsurance())
                                                                        .paymentCycle(request.getPaymentCycle())
                                                                        .paymentPeriod(paymentPeriod)
                                                                        .premium(premium)
                                                                        .maxCompensation(insurance.getMaxCompensation())
                                                                        .subscriptionFilePath(subscriptionFilePath)
                                                                        .state(InsuranceApplicationState.PROCESSING)
                                                                        .build();
        insuranceApplicationRepository.save(insuranceApplication);
    }

    @Override
    public List<InsuranceApplicationInfoResponse> getInsuranceApplicationInfoList() {
        return insuranceApplicationRepository.findAll().stream()
                .map(InsuranceApplicationInfoResponse::of)
                .toList();
    }

    @Override
    public InsuranceApplicationDetailResponse getInsuranceApplicationDetail(Long insuranceApplicationId) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        List<FamilyHistory> familyHistories =
                familyHistoryService.getFamilyHistoryList(insuranceApplication.getCustomer());
        return InsuranceApplicationDetailResponse.of(insuranceApplication, familyHistories);
    }

    @Override
    public InputStreamResource getSubscription(Long insuranceApplicationId) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        InputStream inputStream = fileService.downloadFile(insuranceApplication.getSubscriptionFilePath());
        return new InputStreamResource(inputStream);
    }

    @Override
    @Transactional
    public void approvalInsuranceApplication(Long insuranceApplicationId,
                                             String reasonOfApproval) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        insuranceApplication.setReasonOfApproval(reasonOfApproval);
        insuranceApplication.setState(InsuranceApplicationState.APPROVAL);

        contractService.createContract(insuranceApplication);
    }

    @Override
    @Transactional
    public void rejectionInsuranceApplication(Long insuranceApplicationId, String reasonOfRejection) {
        InsuranceApplication insuranceApplication =
                insuranceApplicationQueryService.getInsurance(insuranceApplicationId);
        insuranceApplication.setReasonOfApproval(reasonOfRejection);
        insuranceApplication.setState(InsuranceApplicationState.REJECTION);
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
