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

public interface SurveyService {
    SurveyResponse getSurvey(Long ccid);
    void createSurvey(Long ccid, CreateSurveyRequest request);
    void updateSurvey(Long ccid, UpdateSurveyRequest request);
    InputStreamResource getReport(Long ccid);


}
