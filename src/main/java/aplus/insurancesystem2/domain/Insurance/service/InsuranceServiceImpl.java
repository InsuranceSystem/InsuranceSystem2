package aplus.insurancesystem2.domain.Insurance.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.Insurance.dto.request.DesignInsuranceRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceDetailResponse;
import aplus.insurancesystem2.domain.Insurance.entity.Guarantee;
import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.Insurance.entity.Terms;
import aplus.insurancesystem2.domain.Insurance.exception.InsuranceNotFoundException;
import aplus.insurancesystem2.domain.Insurance.repository.GuaranteeRepository;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceRepository;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import aplus.insurancesystem2.domain.customer.service.TermsQueryService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final GuaranteeRepository guaranteeRepository;
    private final TermsRepository termsRepository;
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
    public Insurance getInsurance(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                                  .orElseThrow(InsuranceNotFoundException::new);
    }

    @Override
    @Transactional
    public void designInsurance(DesignInsuranceRequest request) {
        Insurance insurance = Insurance.builder()
                                       .insuranceName(request.getInsuranceName())
                                       .type(request.getType())
                                       .maxCompensation(request.getMaxCompensation())
                                       .periodOfInsurance(request.getPeriodOfInsurance())
                                       .paymentCycle(request.getPaymentCycle())
                                       .paymentPeriod(request.getPaymentPeriod())
                                       .ageOfTarget(request.getAgeOfTarget())
                                       .basicPremium(request.getBasicPremium())
                                       .rate(request.getRate())
                                       .distributionStatus(request.isDistributionStatus())
                                       .insuranceClausePeriod(request.getInsuranceClausePeriod())
                                       .precaution(request.getPrecaution())
                                       .authorization(request.isAuthorization())
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

}