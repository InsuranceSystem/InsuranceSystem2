package aplus.insurancesystem.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem.domain.Insurance.dto.request.DesignInsuranceRequest;
import aplus.insurancesystem.domain.Insurance.dto.request.UpdateInsuranceRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceDetailResponse;

public interface InsuranceService {
    InsuranceDetailResponse getInsuranceDetail(Long insuranceId);
    List<InsuranceDetailResponse> getInsuranceDetailList();
    void designInsurance(DesignInsuranceRequest request);

    void updateInsurance(Long insuranceId, UpdateInsuranceRequest request);

    void deleteInsurance(Long insuranceId);

    void registerInsurance(Long insuranceId);
}
