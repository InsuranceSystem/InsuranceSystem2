package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Terms;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService{
    private final TermsRepository termsRepository;

    @Override
    public List<Terms> getTermsList() {
        return termsRepository.findAll();
    }

    @Override
    public String createTerms(termsCreateRequest termsDto){
        Terms terms = new Terms(termsDto);
        termsRepository.save(terms);
        return "Success";
    }

    @Override
    public List<TermInfoResponse> getInsuranceTerms(Long insuranceId) {
        return termsRepository.findAllByInsuranceId(insuranceId)
            .stream()
            .map(TermInfoResponse::of)
            .toList();
    }
}
