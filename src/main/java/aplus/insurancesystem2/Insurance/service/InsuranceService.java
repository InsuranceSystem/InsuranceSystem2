package aplus.insurancesystem2.Insurance.service;

import aplus.insurancesystem2.Insurance.domain.Insurance;
import aplus.insurancesystem2.Insurance.domain.Terms;
import aplus.insurancesystem2.Insurance.dto.request.insuranceCreateRequest;
import aplus.insurancesystem2.Insurance.dto.response.InsuranceInfoResponse;

import java.util.List;
import java.util.Optional;

public interface InsuranceService {
    InsuranceInfoResponse getInsuranceInfo(String insuranceId);
    List<Insurance> getInsuranceList();
    List<Insurance> getInsuranceListApprove();
    List<Insurance> getInsuranceListNotApprove();
    List<Insurance> getInsuranceListByType(String type);
    List<Insurance> getInsuranceListByTypeApprove(String type);
    List<Insurance> getInsuranceListByTypeNotApprove(String type);
    String createInsurance(insuranceCreateRequest insurance);
    String updateInsurance(insuranceCreateRequest insurance, String insuranceId);
    String updateAuthInsurance(String insuranceId);
    String deleteInsurance(String insuranceId);
    List<Optional<Terms>> getTermsListByInsuranceId(String insuranceId);
}
