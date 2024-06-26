package aplus.insurancesystem.domain.Insurance.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;

import aplus.insurancesystem.domain.Insurance.dto.request.ApplyInsuranceRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.MyInsuranceApplicationResponse;

public interface InsuranceApplicationService {

    void applyInsurance(Long insuranceId, ApplyInsuranceRequest request);

    List<InsuranceApplicationInfoResponse> getInsuranceApplicationInfoList();

    InsuranceApplicationDetailResponse getInsuranceApplicationDetail(Long insuranceApplicationId);

    InputStreamResource getSubscription(Long insuranceApplicationId);

    void approvalInsuranceApplication(Long insuranceApplicationId, String reasonOfApproval);

    void rejectionInsuranceApplication(Long insuranceApplicationId, String reasonOfRejection);

    List<MyInsuranceApplicationResponse> getMyInsuranceApplicationList(Long customerId);

    InsuranceApplicationResultResponse getInsuranceApplicationResult(Long insuranceApplicationId);
}
