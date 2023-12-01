package aplus.insurancesystem2.domain.Insurance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.Insurance.exception.InsuranceApplicationNotFoundException;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceApplicationRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceApplicationQueryServiceImpl implements InsuranceApplicationQueryService{

    private final InsuranceApplicationRepository insuranceApplicationRepository;

    @Override
    public InsuranceApplication getInsurance(Long insuranceApplicationId) {
        return insuranceApplicationRepository.findById(insuranceApplicationId)
                                             .orElseThrow(InsuranceApplicationNotFoundException::new);
    }
}
