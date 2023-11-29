package aplus.insurancesystem2.domain.Insurance.service;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;

public interface InsuranceApplicationQueryService {
    InsuranceApplication getInsurance(Long insuranceApplicationId);
}
