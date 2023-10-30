package aplus.insurancesystem2.domain.Insurance.service;

import aplus.insurancesystem2.domain.Insurance.domain.Terms;
import aplus.insurancesystem2.domain.Insurance.dto.request.termsCreateRequest;

import java.util.List;

public interface TermsService {
    List<Terms> getTermsList();
    String createTerms(termsCreateRequest terms);
}
