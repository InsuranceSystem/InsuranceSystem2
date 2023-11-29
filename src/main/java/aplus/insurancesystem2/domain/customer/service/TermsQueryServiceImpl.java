package aplus.insurancesystem2.domain.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.entity.Terms;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import aplus.insurancesystem2.domain.customer.exception.TermsNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TermsQueryServiceImpl implements TermsQueryService {

    private final TermsRepository termsRepository;

    @Override
    public Terms getTerms(Long termsId) {
        return termsRepository.findById(termsId)
                              .orElseThrow(TermsNotFoundException::new);
    }
}
