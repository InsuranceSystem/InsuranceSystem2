package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final InsuranceQueryService insuranceQueryService;
    private final TermsRepository termsRepository;

    @Override
    public List<TermInfoResponse> getInsuranceTerms(Long insuranceId) {
        return termsRepository.findAllByInsurance(insuranceQueryService.getInsurance(insuranceId))
            .stream()
            .map(TermInfoResponse::of)
            .toList();
    }
}
