package aplus.insurancesystem.domain.compensationClaim.service;

import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;
import org.springframework.core.io.InputStreamResource;

public interface SurveyService {
    SurveyResponse getSurvey(Long ccid);
    void createSurvey(Long ccid, CreateSurveyRequest request);
    void updateSurvey(Long ccid, UpdateSurveyRequest request);
    InputStreamResource getReport(Long ccid);


}
