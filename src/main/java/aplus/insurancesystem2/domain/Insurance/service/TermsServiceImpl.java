package aplus.insurancesystem2.domain.Insurance.service;

import aplus.insurancesystem2.domain.Insurance.domain.Terms;
import aplus.insurancesystem2.domain.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
