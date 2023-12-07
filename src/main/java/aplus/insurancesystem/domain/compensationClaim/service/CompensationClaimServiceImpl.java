package aplus.insurancesystem.domain.compensationClaim.service;

import aplus.insurancesystem.domain.Insurance.entity.insurance.Insurance;
import aplus.insurancesystem.domain.Insurance.service.InsuranceQueryService;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CompensationClaimResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;
import aplus.insurancesystem.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import aplus.insurancesystem.domain.compensationClaim.entity.Survey;
import aplus.insurancesystem.domain.compensationClaim.exception.CarAccidentNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.exception.CompensationClaimNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.exception.SurveyNotFoundException;
import aplus.insurancesystem.domain.compensationClaim.repository.CarAccidentRepository;
import aplus.insurancesystem.domain.compensationClaim.repository.CompensationClaimRepository;
import aplus.insurancesystem.domain.compensationClaim.repository.SurveyRepository;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import aplus.insurancesystem.domain.customer.service.CustomerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompensationClaimServiceImpl implements CompensationClaimService {
    private final CompensationClaimRepository compensationClaimRepository;
    private final CarAccidentRepository carAccidentRepository;
    private final SurveyRepository surveyRepository;
    private final InsuranceQueryService insuranceQueryService;
    private final CustomerQueryService customerQueryService;
    @Override
    public List<CompensationClaimResponse> getAllCompensationClaim() {
        return compensationClaimRepository.findAll()
                .stream()
                .map(CompensationClaimResponse::of)
                .toList();
    }
    @Override
    public List<CompensationClaimResponse> getCompensationClaim(Long customerId) {
        return compensationClaimRepository.findAllByCustomerId(customerId)
                .stream()
                .map(CompensationClaimResponse::of)
                .toList();
    }
    @Override
    public CompensationClaimResponse getCompensationClaimDetail(Long ccid) {
        return compensationClaimRepository.findById(ccid)
                .map(CompensationClaimResponse::of)
                .orElseThrow(CompensationClaimNotFoundException::new);
    }
    @Override
    public CarAccidentResponse getCarAccidentDetail(Long ccid) {
        return carAccidentRepository.findById(ccid)
                .map(CarAccidentResponse::of)
                .orElseThrow(CarAccidentNotFoundException::new);
    }
    @Override
    @Transactional
    public void createCompensationClaim(CreateCompensationClaimRequest request) {
        Insurance insurance = insuranceQueryService.getInsurance(request.getInsuranceId());
        Customer customer = customerQueryService.getCustomer(request.getCustomerId());
        CompensationClaim compensationClaim = CompensationClaim.builder()
                .insurance(insurance)
                .customer(customer)
                .receptionistName(request.getReceptionistName())
                .receptionistPNumber(request.getReceptionistPNumber())
                .relationshipOfContractor(request.getRelationshipOfContractor())
                .documentFilePath(request.getDocumentFilePath())
                .bank(request.getBank())
                .accountNumber(request.getAccountNumber())
                .accountHolderName(request.getAccountHolderName())
                .build();
        compensationClaimRepository.save(compensationClaim);
    }

    @Override
    @Transactional
    public void createCarAccident(CreateCarAccidentRequest request) {
        Insurance insurance = insuranceQueryService.getInsurance(request.getInsuranceId());
        Customer customer = customerQueryService.getCustomer(request.getCustomerId());
        CompensationClaim compensationClaim = CompensationClaim.builder()
                .insurance(insurance)
                .customer(customer)
                .receptionistName(request.getReceptionistName())
                .receptionistPNumber(request.getReceptionistPNumber())
                .relationshipOfContractor(request.getRelationshipOfContractor())
                .documentFilePath(request.getDocumentFilePath())
                .bank(request.getBank())
                .accountNumber(request.getAccountNumber())
                .accountHolderName(request.getAccountHolderName())
                .build();
        compensationClaimRepository.saveAndFlush(compensationClaim);
        CarAccident carAccident = CarAccident.builder()
                .id(compensationClaim.getId())
                .compensationClaim(compensationClaim)
                .type(request.getType())
                .dateTime(request.getDateTime())
                .place(request.getPlace())
                .carNumber(request.getCarNumber())
                .driverName(request.getDriverName())
                .licenseNumber(request.getLicenseNumber())
                .accidentDetail(request.getAccidentDetail())
                .build();
        carAccidentRepository.save(carAccident);
    }

    @Override
    public SurveyResponse getSurvey(Long ccid) {
        return surveyRepository.findById(ccid)
        .map(SurveyResponse::of)
        .orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    @Transactional
    public void createSurvey(CreateSurveyRequest request) {
        CompensationClaim compensationClaim = compensationClaimRepository.findById(request.getId()).orElseThrow(CompensationClaimNotFoundException::new);
        Survey survey = Survey.builder()
                .id(request.getId())
                .compensationClaim(compensationClaim)
                .managerName(request.getManagerName())
                .reportFilePath(request.getReportFilePath())
                .surveyFee(request.getSurveyFee())
                .decisionMoney(request.getDecisionMoney())
                .responsibility(request.getResponsibility())
                .responsibilityReason(request.getResponsibilityReason())
                .build();
        surveyRepository.save(survey);
    }

    @Override
    @Transactional
    public void updateSurvey(Long ccid, UpdateSurveyRequest request) {
        Survey survey = surveyRepository.findById(ccid).orElseThrow(SurveyNotFoundException::new);
        survey.setManagerName(request.getManagerName());
        survey.setReportFilePath(request.getReportFilePath());
        survey.setSurveyFee(request.getSurveyFee());
        survey.setDecisionMoney(request.getDecisionMoney());
        survey.setResponsibility(request.getResponsibility());
        survey.setResponsibilityReason(request.getResponsibilityReason());
    }
}
