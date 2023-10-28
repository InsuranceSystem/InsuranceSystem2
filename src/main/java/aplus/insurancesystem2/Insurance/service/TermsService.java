package aplus.insurancesystem2.Insurance.service;

import aplus.insurancesystem2.Insurance.domain.Terms;
import aplus.insurancesystem2.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.Insurance.dto.response.termsInfoResponse;

import java.util.List;
import java.util.Optional;

public interface TermsService {
    List<Terms> getTermsList();
    String createTerms(termsCreateRequest terms);
}
