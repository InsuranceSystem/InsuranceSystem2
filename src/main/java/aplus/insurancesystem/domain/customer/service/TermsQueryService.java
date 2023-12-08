package aplus.insurancesystem.domain.customer.service;

import aplus.insurancesystem.domain.Insurance.entity.Terms;

public interface TermsQueryService {
    Terms getTerms(Long termsId);
}
