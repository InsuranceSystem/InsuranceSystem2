package aplus.insurancesystem2.domain.Insurance.service;

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;

public interface InsuranceQueryService {
    Insurance getInsurance(Long insuranceId);
}
