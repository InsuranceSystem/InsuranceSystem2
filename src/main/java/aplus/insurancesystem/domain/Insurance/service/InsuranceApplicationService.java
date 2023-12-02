package aplus.insurancesystem.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.MyInsuranceApplicationResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.SubscriptionFilePathResponse;

public interface InsuranceApplicationService {

    void applyInsurance(Long insuranceId, CreateInsuranceApplicationRequest request);

    List<InsuranceApplicationInfoResponse> getInsuranceApplicationList();

    InsuranceApplicationDetailResponse getInsuranceApplication(Long insuranceApplicationId);

    SubscriptionFilePathResponse getSubscription(Long insuranceApplicationId);

    void approvalInsuranceApplication(Long insuranceApplicationId, String reasonOfApproval);

    void rejectionInsuranceApplication(Long insuranceApplicationId, String reasonOfRejection);

    List<MyInsuranceApplicationResponse> getMyInsuranceApplicationList(Long customerId);

    InsuranceApplicationResultResponse getInsuranceApplicationResult(Long insuranceApplicationId);
}
