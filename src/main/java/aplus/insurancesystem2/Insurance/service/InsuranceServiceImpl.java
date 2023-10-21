package aplus.insurancesystem2.Insurance.service;

import aplus.insurancesystem2.Insurance.dto.response.InsuranceInfoResponse;
import aplus.insurancesystem2.Insurance.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService{
    private final InsuranceRepository insuranceRepository;
    @Override
    public InsuranceInfoResponse getInsuranceInfo(String insuranceId) {
        return insuranceRepository.findById(insuranceId)
                .map(InsuranceInfoResponse::of)
                .orElse(null);
    }
}
