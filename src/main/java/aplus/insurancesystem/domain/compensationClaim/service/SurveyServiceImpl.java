package aplus.insurancesystem.domain.compensationClaim.service;


import aplus.insurancesystem.common.service.Esms;
import aplus.insurancesystem.common.service.FileService;
import aplus.insurancesystem.common.service.MessageService;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;
import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import aplus.insurancesystem.domain.compensationClaim.entity.Survey;
import aplus.insurancesystem.domain.compensationClaim.exception.CompensationClaimNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.exception.SurveyNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.repository.CompensationClaimRepository;
import aplus.insurancesystem.domain.compensationClaim.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final CompensationClaimRepository compensationClaimRepository;
    private final SurveyRepository surveyRepository;
    private final FileService fileService;
    private final MessageService messageService;


    @Override
    public SurveyResponse getSurvey(Long ccid) {
        return surveyRepository.findById(ccid)
        .map(SurveyResponse::of)
        .orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    @Transactional
    public void createSurvey(Long ccid, CreateSurveyRequest request) {
        CompensationClaim compensationClaim = compensationClaimRepository.findById(ccid).orElseThrow(CompensationClaimNotFoundException::new);

        MultipartFile reportFile = request.getReportFile();
        String reportFilePath = reportFile.getOriginalFilename() + LocalDateTime.now();
        fileService.uploadFile(reportFile, reportFilePath);

        Survey survey = Survey.builder()
                .id(ccid)
                .compensationClaim(compensationClaim)
                .managerName(request.getManagerName())
                .reportFilePath(reportFilePath)
                .surveyFee(request.getSurveyFee())
                .decisionMoney(request.getDecisionMoney())
                .responsibility(request.getResponsibility())
                .responsibilityReason(request.getResponsibilityReason())
                .build();
        compensationClaim.setSurveyed(true);
        compensationClaimRepository.save(compensationClaim);
        surveyRepository.save(survey);

        String phoneNumber = compensationClaim.getReceptionistPNumber().replaceAll("-", "");
        messageService.sendOne(phoneNumber, Esms.SURVEY_COMPLETED);
    }

    @Override
    @Transactional
    public void updateSurvey(Long ccid, UpdateSurveyRequest request) {
        Survey survey = surveyRepository.findById(ccid).orElseThrow(SurveyNotFoundException::new);

        MultipartFile reportFile = request.getReportFile();
        String reportFilePath = reportFile.getOriginalFilename() + LocalDateTime.now();
        fileService.uploadFile(reportFile, reportFilePath);

        survey.setManagerName(request.getManagerName());
        survey.setReportFilePath(reportFilePath);
        survey.setSurveyFee(request.getSurveyFee());
        survey.setDecisionMoney(request.getDecisionMoney());
        survey.setResponsibility(request.getResponsibility());
        survey.setResponsibilityReason(request.getResponsibilityReason());
    }

    @Override
    public InputStreamResource getReport(Long ccid) {
        Survey survey = surveyRepository.findById(ccid).orElseThrow(SurveyNotFoundException::new);
        InputStream inputStream = fileService.downloadFile(survey.getReportFilePath());
        return new InputStreamResource(inputStream);
    }
}
