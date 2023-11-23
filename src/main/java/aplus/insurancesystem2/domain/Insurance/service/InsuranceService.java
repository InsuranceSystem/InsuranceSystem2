package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceDetailResponse;

public interface InsuranceService {
    InsuranceDetailResponse getInsuranceInfo(Long insuranceId);
    List<InsuranceDetailResponse> getInsuranceList();
}
