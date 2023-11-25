package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceDetailResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Insurance;

public interface InsuranceService {
    InsuranceDetailResponse getInsuranceInfo(Long insuranceId);
    List<InsuranceDetailResponse> getInsuranceList();
    Insurance getInsurance(Long insuranceId);
}
