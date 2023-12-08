package aplus.insurancesystem.domain.compensationClaim.service;

import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CompensationClaimResponse;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface CompensationClaimService {
    List<CompensationClaimResponse> getAllCompensationClaim();
    List<CompensationClaimResponse> getCompensationClaim(Long customerId);
    CompensationClaimResponse getCompensationClaimDetail(Long ccid);
    InputStreamResource getDocument(Long ccid);
    void createCompensationClaim(Long contractId, CreateCompensationClaimRequest request);
}
