package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.dto.request.CalculatePremiumRequest;
import aplus.insurancesystem2.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.MyInsuranceApplicationResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.SubscriptionFilePathResponse;

public interface InsuranceApplicationService {

    void applyInsurance(Long insuranceId, CreateInsuranceApplicationRequest request);

    List<InsuranceApplicationInfoResponse> getInsuranceApplicationList();

    InsuranceApplicationDetailResponse getInsuranceApplication(Long insuranceApplicationId);

    SubscriptionFilePathResponse getSubscription(Long insuranceApplicationId);

    InsuranceApplicationInfoResponse getInsuranceApplicationInfo(Long insuranceApplicationId);

    void calculatePremium(CalculatePremiumRequest request);

    void approvalInsuranceApplication(Long insuranceApplicationId);

    void rejectionInsuranceApplication(Long insuranceApplicationId, String reasonOfRejection);

    List<MyInsuranceApplicationResponse> getMyInsuranceApplicationList(Long customerId);

    InsuranceApplicationResultResponse getInsuranceApplicationResult(Long insuranceApplicationId);
}