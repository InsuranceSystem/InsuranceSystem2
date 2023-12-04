package aplus.insurancesystem.domain.Insurance.service;

import aplus.insurancesystem.domain.Insurance.entity.Insurance;

public interface InsuranceQueryService {
    Insurance getInsurance(Long insuranceId);
}
