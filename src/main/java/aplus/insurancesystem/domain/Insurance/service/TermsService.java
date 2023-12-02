package aplus.insurancesystem.domain.Insurance.service;

import java.util.List;

import aplus.insurancesystem.domain.Insurance.dto.request.CreateTermsRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.TermInfoResponse;

public interface TermsService {
    List<TermInfoResponse> getInsuranceTermsList(Long insuranceId);

    List<TermInfoResponse> getTermsList();

    void createTerms(CreateTermsRequest request);
}
