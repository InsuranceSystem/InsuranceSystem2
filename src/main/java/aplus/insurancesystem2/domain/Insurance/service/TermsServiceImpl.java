package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.request.CreateTermsRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Terms;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final InsuranceQueryService insuranceQueryService;
    private final TermsRepository termsRepository;

    @Override
    public List<TermInfoResponse> getInsuranceTermsList(Long insuranceId) {
        return termsRepository.findAllByInsurance(insuranceQueryService.getInsurance(insuranceId))
            .stream()
            .map(TermInfoResponse::of)
            .toList();
    }

    @Override
    public List<TermInfoResponse> getTermsList() {
        return termsRepository.findAll()
            .stream()
            .map(TermInfoResponse::of)
            .toList();
    }

    @Override
    @Transactional
    public void createTerms(CreateTermsRequest request) {
        termsRepository.save(Terms.builder()
                                  .termsName(request.getTermsName())
                                  .calculatedMoneyMethod(request.getCalculatedMoneyMethod())
                                  .termsContent(request.getTermsContent())
                                  .build());
    }
}
