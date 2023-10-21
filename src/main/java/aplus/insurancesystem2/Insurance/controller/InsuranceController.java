package aplus.insurancesystem2.Insurance.controller;

import aplus.insurancesystem2.Insurance.dto.response.InsuranceInfoResponse;
import aplus.insurancesystem2.Insurance.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    //보험 상세조회
    @GetMapping("/{id}")
    public InsuranceInfoResponse getInsurance(@PathVariable("id") String insuranceId) {
        return insuranceService.getInsuranceInfo(insuranceId);
    }

    //테마별 보험 조회
    //@GetMapping("/{type}")
    //public InsuranceInfoResponse getInsuranceListByType(@PathVariable("type") String insuranceId) {
    //    return insuranceService.getInsuranceListByType(insuranceId);
    //}

    //전체 보험 조회
    //@GetMapping("/all")
    //public InsuranceInfoResponse getInsuranceList() {
    //    return insuranceService.getInsuranceList(insuranceId);
    //}
}
