package aplus.insurancesystem2.Insurance.service;

import aplus.insurancesystem2.Insurance.dto.response.InsuranceInfoResponse;

public interface InsuranceService {
    InsuranceInfoResponse getInsuranceInfo(String insuranceId);
}
