package aplus.insurancesystem.domain.Insurance.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem.domain.Insurance.dto.request.DesignInsuranceRequest;
import aplus.insurancesystem.domain.Insurance.dto.request.UpdateInsuranceRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceDetailResponse;
import aplus.insurancesystem.domain.Insurance.entity.Guarantee;
import aplus.insurancesystem.domain.Insurance.entity.Insurance;
import aplus.insurancesystem.domain.Insurance.entity.Terms;
import aplus.insurancesystem.domain.Insurance.exception.InsuranceNotFoundException;
import aplus.insurancesystem.domain.Insurance.repository.GuaranteeRepository;
import aplus.insurancesystem.domain.Insurance.repository.InsuranceRepository;
import aplus.insurancesystem.domain.customer.service.TermsQueryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final GuaranteeRepository guaranteeRepository;
    private final InsuranceQueryService insuranceQueryService;
    private final TermsQueryService termsQueryService;

    @Override
    public InsuranceDetailResponse getInsuranceInfo(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                                  .map(InsuranceDetailResponse::of)
                                  .orElseThrow(InsuranceNotFoundException::new);
    }

    @Override
    public List<InsuranceDetailResponse> getInsuranceList() {
        return insuranceRepository.findAllWithGuarantees()
                                  .stream()
                                  .map(InsuranceDetailResponse::of)
                                  .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void designInsurance(DesignInsuranceRequest request) {
        Insurance insurance = Insurance.builder()
                                       .insuranceName(request.getInsuranceName())
                                       .type(request.getType())
                                       .maxCompensation(request.getMaxCompensation())
                                       .periodOfInsurance(request.getPeriodOfInsurance())
                                       .ageOfTarget(request.getAgeOfTarget())
                                       .basicPremium(request.getBasicPremium())
                                       .distributionStatus(request.isDistributionStatus())
                                       .insuranceClausePeriod(request.getInsuranceClausePeriod())
                                       .precaution(request.getPrecaution())
                                       .authorization(false)
                                       .build();

        insuranceRepository.save(insurance);

        Arrays.stream(request.getTermsIdList().split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .forEach(termsId -> {
                    Terms terms = termsQueryService.getTerms(termsId);
                    guaranteeRepository.save(new Guarantee(insurance, terms));
                });
    }

    @Override
    @Transactional
    public void updateInsurance(Long insuranceId, UpdateInsuranceRequest request) {
        Insurance insurance = insuranceQueryService.getInsurance(insuranceId);
        insurance.setInsuranceName(request.getInsuranceName());
        insurance.setType(request.getType());
        insurance.setMaxCompensation(request.getMaxCompensation());
        insurance.setPeriodOfInsurance(request.getPeriodOfInsurance());
        insurance.setAgeOfTarget(request.getAgeOfTarget());
        insurance.setBasicPremium(request.getBasicPremium());
        insurance.setDistributionStatus(request.isDistributionStatus());
        insurance.setInsuranceClausePeriod(request.getInsuranceClausePeriod());
        insurance.setPrecaution(request.getPrecaution());

        guaranteeRepository.deleteAllByInsuranceId(insuranceId);

        Arrays.stream(request.getTermsIdList().split(","))
              .map(String::trim)
              .map(Long::parseLong)
              .forEach(termsId -> {
                  Terms terms = termsQueryService.getTerms(termsId);
                  guaranteeRepository.save(new Guarantee(insurance, terms));
              });
    }

    @Override
    @Transactional
    public void deleteInsurance(Long insuranceId) {
        Insurance insurance = insuranceQueryService.getInsurance(insuranceId);
        insuranceRepository.delete(insurance);
    }

    @Override
    @Transactional
    public void registerInsurance(Long insuranceId) {
        Insurance insurance = insuranceQueryService.getInsurance(insuranceId);
        insurance.setAuthorization(true);
    }

}