package aplus.insurancesystem2.domain.Insurance.service;

import aplus.insurancesystem2.domain.Insurance.domain.Guarantee;
import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import aplus.insurancesystem2.domain.Insurance.domain.Terms;
import aplus.insurancesystem2.domain.Insurance.dto.request.insuranceCreateRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceInfoResponse;
import aplus.insurancesystem2.domain.Insurance.exception.InsuranceNotFoundException;
import aplus.insurancesystem2.domain.Insurance.repository.GuaranteeRepository;
import aplus.insurancesystem2.domain.Insurance.repository.InsuranceRepository;
import aplus.insurancesystem2.domain.Insurance.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService{
    private final InsuranceRepository insuranceRepository;
    private final GuaranteeRepository guaranteeRepository;
    private final TermsRepository termsRepository;
    @Override
    public InsuranceInfoResponse getInsuranceInfo(String insuranceId) {
        return insuranceRepository.findById(insuranceId)
                .map(InsuranceInfoResponse::of)
                .orElseThrow(InsuranceNotFoundException::new);
    }

    @Override
    public List<Insurance> getInsuranceListByType(String type) {
        return insuranceRepository.findByType(type);
    }

    @Override
    public List<Insurance> getInsuranceListByTypeApprove(String type) {
        List<Insurance> insuranceList = insuranceRepository.findByType(type);
        List<Insurance> insuranceListApprove = insuranceList.stream()
                .filter(insurance -> insurance.isAuthorization())
                .collect(Collectors.toList());
        return insuranceListApprove;
    }

    @Override
    public List<Insurance> getInsuranceListByTypeNotApprove(String type) {
        List<Insurance> insuranceList = insuranceRepository.findByType(type);
        List<Insurance> insuranceListNotApprove = insuranceList.stream()
                .filter(insurance -> !insurance.isAuthorization())
                .collect(Collectors.toList());
        return insuranceListNotApprove;
    }

    @Override
    public List<Insurance> getInsuranceList() {
        return insuranceRepository.findAll();
    }

    @Override
    public List<Insurance> getInsuranceListApprove() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        List<Insurance> insuranceListApprove = insuranceList.stream()
                .filter(insurance -> insurance.isAuthorization())
                .collect(Collectors.toList());
        return insuranceListApprove;
    }

    //
    @Override
    public List<Insurance> getInsuranceListNotApprove() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        List<Insurance> insuranceListNotApprove = insuranceList.stream()
                .filter(insurance -> !insurance.isAuthorization())
                .collect(Collectors.toList());
        return insuranceListNotApprove;
    }

    @Override
    public String createInsurance(insuranceCreateRequest insuranceDto){
        Insurance insurance = new Insurance(insuranceDto);
        insuranceRepository.save(insurance);
        String[] termsIDListSplit = insurance.getTermsIDList().split(",");
        Guarantee guarantee = new Guarantee();
        for (int i = 0; i < termsIDListSplit.length; i++) {
            guarantee.setInsuranceID(insurance.getId());
            guarantee.setTermsID(termsIDListSplit[i]);
            guaranteeRepository.save(guarantee);
        }
        return "Success";
    }

    @Override
    public String updateInsurance(insuranceCreateRequest insuranceDto, String insuranceId) {
        Optional<Insurance> optionalInsurance = insuranceRepository.findById(insuranceId);
        if (optionalInsurance.isPresent()) {
            Insurance insurance = optionalInsurance.get();
            insurance.setInsuranceName(insuranceDto.getInsuranceName());
            insurance.setType(insuranceDto.getType());
            insurance.setMaxCompensation(insuranceDto.getMaxCompensation());
            insurance.setPeriodOfInsurance(insuranceDto.getPeriodOfInsurance());
            insurance.setPaymentCycle(insuranceDto.getPaymentCycle());
            insurance.setPaymentPeriod(insuranceDto.getPaymentPeriod());
            insurance.setAgeOfTarget(insuranceDto.getAgeOfTarget());
            insurance.setBasicPremium(insuranceDto.getBasicPremium());
            insurance.setRate(insuranceDto.getRate());
            insurance.setDistributionStatus(insuranceDto.isDistributionStatus());
            insurance.setTermsIDList(insuranceDto.getTermsIDList());
            insurance.setInsuranceClausePeriod(insuranceDto.getInsuranceClausePeriod());
            insurance.setPrecaution(insuranceDto.getPrecaution());
            insurance.setAuthorization(insuranceDto.isAuthorization());
            insuranceRepository.save(insurance);
            return "학생단체 정보를 수정했습니다";
        } else {
            return "Fail";
        }
    }

    @Override
    public String updateAuthInsurance(String insuranceId) {
        Optional<Insurance> insurance = insuranceRepository.findById(insuranceId);
        if(insurance.isPresent()) {
            Insurance updateInsurance = insurance.get();
            updateInsurance.setAuthorization(!updateInsurance.isAuthorization());
            insuranceRepository.save(updateInsurance);
        } else {
            return "Fail";
        }
        return null;
    }

    @Override
    public String deleteInsurance(String insuranceId) {
        Optional<Insurance> insurance = insuranceRepository.findById(insuranceId);
        if(insurance.isPresent()) {
            insuranceRepository.deleteById(insuranceId);
        } else {
            return "Fail";
        }
        return null;
    }

    @Override
    public List<Optional<Terms>> getTermsListByInsuranceId(String insuranceId) {
        List<Guarantee> guaranteeList = guaranteeRepository.findAllByInsuranceID(insuranceId);
        List<Optional<Terms>> termsList = new ArrayList<>();
        for (int i = 0; i < guaranteeList.size(); i++) {
            termsList.add(termsRepository.findById(guaranteeList.get(i).getTermsID()));
        }
        return termsList;
    }
}
