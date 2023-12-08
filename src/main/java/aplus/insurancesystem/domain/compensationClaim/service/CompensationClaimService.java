package aplus.insurancesystem.domain.compensationClaim.service;

import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CompensationClaimResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface CompensationClaimService {
    List<CompensationClaimResponse> getAllCompensationClaim();
    List<CompensationClaimResponse> getCompensationClaim(Long customerId);
    CompensationClaimResponse getCompensationClaimDetail(Long ccid);
    CarAccidentResponse getCarAccidentDetail(Long ccid);
    InputStreamResource getDocument(Long ccid);
    void createCompensationClaim(Long contractId, CreateCompensationClaimRequest request);
    void createCarAccident(Long contractId,CreateCarAccidentRequest request);

}
