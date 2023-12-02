package aplus.insurancesystem.domain.Insurance.service;

import aplus.insurancesystem.domain.Insurance.entity.InsuranceApplication;

public interface InsuranceApplicationQueryService {
    InsuranceApplication getInsurance(Long insuranceApplicationId);
}
