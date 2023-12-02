package aplus.insurancesystem.domain.compensationClaim.service;

import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CompensationClaimResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;

import java.util.List;

public interface CompensationClaimService {
    List<CompensationClaimResponse> getAllCompensationClaim();
    List<CompensationClaimResponse> getCompensationClaim(Long customerId);
    CompensationClaimResponse getCompensationClaimDetail(Long ccid);
    CarAccidentResponse getCarAccidentDetail(Long ccid);
    void createCompensationClaim(CreateCompensationClaimRequest request);
    void createCarAccident(CreateCarAccidentRequest request);
    SurveyResponse getSurvey(Long ccid);
    void createSurvey(CreateSurveyRequest request);
    void updateSurvey(Long ccid, UpdateSurveyRequest request);
}
