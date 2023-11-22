package aplus.insurancesystem2.domain.Insurance.controller;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.Insurance.domain.Insurance;
import aplus.insurancesystem2.domain.Insurance.domain.Terms;
import aplus.insurancesystem2.domain.Insurance.dto.request.insuranceCreateRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceInfoResponse;
import aplus.insurancesystem2.domain.Insurance.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @Operation(summary = "보험 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "상세 보험 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<InsuranceInfoResponse>> getInsurance(
            @PathVariable("id") String insuranceId) {
        return SuccessResponse.of(insuranceService.getInsuranceInfo(insuranceId))
                .asHttp(HttpStatus.OK);
    }

    //테마별 보험 조회
//    @GetMapping("/{type}")
//    public List<Insurance> getInsuranceListByType(@PathVariable("type") String type) {
//        return insuranceService.getInsuranceListByType(type);
//    }
//    //승인된 테마별 보험 조회
//    @GetMapping("/{type}/approve")
//    public List<Insurance> getInsuranceListByTypeApprove(@PathVariable("type") String type) {
//        return insuranceService.getInsuranceListByTypeApprove(type);
//    }
    //설계중인 테마별 보험 조회
//    @GetMapping("/{type}/approve")
//    public List<Insurance> getInsuranceListByTypeNotApprove(@PathVariable("type") String type) {
//        return insuranceService.getInsuranceListByTypeNotApprove(type);
//    }
    @Operation(summary = "전체 보험 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "전체 보험 정보 list 반환")
    })
    @GetMapping("/all")
    public List<Insurance> getInsuranceList() {
        return insuranceService.getInsuranceList();
    }
    //승인된 전체 보험 조회
//    @GetMapping("/all/approve")
//    public List<Insurance> getInsuranceListApprove() {
//        return insuranceService.getInsuranceListApprove();
//    }
    //설계중인 전체 보험 조회
//    @GetMapping("/all/approve")
//    public List<Insurance> getInsuranceListNotApprove() {
//        return insuranceService.getInsuranceListNotApprove();
//    }
/*
    //보험별 약관 조회
    @GetMapping("/terms/{id}")
    public List<Optional<Terms>> getTermsListByInsuranceId(@PathVariable("id") String insuranceId) {
        return insuranceService.getTermsListByInsuranceId(insuranceId);
    }
    //새 보험 등록
    @PostMapping("/add")
    public String createInsurance(@Valid @RequestPart("dto") insuranceCreateRequest insurance) {
        return insuranceService.createInsurance(insurance);
    }
    //보험 수정
    @PostMapping("/update/{id}")
    public String updateInsurance(@PathVariable("id") String insuranceId, @Valid @RequestPart("dto") insuranceCreateRequest insurance) {
        return insuranceService.updateInsurance(insurance, insuranceId);
    }
    //설계한 보험 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteInsurance(@PathVariable("id") String insuranceId) {
        return insuranceService.deleteInsurance(insuranceId);
    }
    //설계한 보험 인가 등록 or 판매중인 보험 인가 취소(판매중지)
    @PostMapping("/updateAuth/{id}")
    public String updateAuthInsurance(@PathVariable("id") String insuranceId) {
        return insuranceService.updateAuthInsurance(insuranceId);
    }
 */
}
