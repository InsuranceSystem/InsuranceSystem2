package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.dto.request.DesignInsuranceRequest;
import aplus.insurancesystem2.domain.Insurance.dto.request.UpdateInsuranceRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceDetailResponse;

public interface InsuranceService {
    InsuranceDetailResponse getInsuranceInfo(Long insuranceId);
    List<InsuranceDetailResponse> getInsuranceList();
    void designInsurance(DesignInsuranceRequest request);

    void updateInsurance(Long insuranceId, UpdateInsuranceRequest request);
}
