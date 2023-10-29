package aplus.insurancesystem2.Insurance.service;

import aplus.insurancesystem2.Insurance.domain.Terms;
import aplus.insurancesystem2.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.Insurance.dto.response.InsuranceInfoResponse;
import aplus.insurancesystem2.Insurance.dto.response.termsInfoResponse;
import aplus.insurancesystem2.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
