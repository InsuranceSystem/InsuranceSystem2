package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.dto.request.termsCreateRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Terms;

public interface TermsService {
    List<Terms> getTermsList();
    String createTerms(termsCreateRequest terms);

    List<TermInfoResponse> getInsuranceTerms(Long insuranceId);
}
