package aplus.insurancesystem2.domain.Insurance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.Insurance.exception.InsuranceNotFoundException;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceQueryServiceImpl implements InsuranceQueryService {

    private final InsuranceRepository insuranceRepository;

    @Override
    public Insurance getInsurance(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                                  .orElseThrow(InsuranceNotFoundException::new);
    }
}
