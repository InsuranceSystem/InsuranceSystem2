package aplus.insurancesystem2.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;

public interface TermsService {
    List<TermInfoResponse> getInsuranceTerms(Long insuranceId);
}
