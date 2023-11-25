package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceDetailResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.Insurance.exception.InsuranceNotFoundException;
import aplus.insurancesystem2.domain.Insurance.repository.GuaranteeRepository;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceRepository;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final GuaranteeRepository guaranteeRepository;
    private final TermsRepository termsRepository;

    @Override
    public InsuranceDetailResponse getInsuranceInfo(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                                  .map(InsuranceDetailResponse::of)
                                  .orElseThrow(InsuranceNotFoundException::new);
    }

    @Override
    public List<InsuranceDetailResponse> getInsuranceList() {
        return insuranceRepository.findAllWithGuarantees()
                                  .stream()
                                  .map(InsuranceDetailResponse::of)
                                  .collect(Collectors.toList());
    }

    @Override
    public Insurance getInsurance(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                                  .orElseThrow(InsuranceNotFoundException::new);
    }

}