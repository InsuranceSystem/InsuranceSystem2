package aplus.insurancesystem.domain.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem.domain.Insurance.entity.Terms;
import aplus.insurancesystem.domain.Insurance.repository.TermsRepository;
import aplus.insurancesystem.domain.customer.exception.TermsNotFoundException;
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
