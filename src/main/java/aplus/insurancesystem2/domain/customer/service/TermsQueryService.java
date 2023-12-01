package aplus.insurancesystem2.domain.customer.service;

import aplus.insurancesystem2.domain.Insurance.entity.Terms;

public interface TermsQueryService {
    Terms getTerms(Long termsId);
}
