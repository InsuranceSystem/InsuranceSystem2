package aplus.insurancesystem2.domain.Insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceDetailResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem2.domain.Insurance.service.InsuranceService;
import aplus.insurancesystem2.domain.Insurance.service.TermsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final TermsService termsService;

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
    public ResponseEntity<SuccessResponse<InsuranceDetailResponse>> getInsurance(
            @PathVariable("id") Long insuranceId) {
        return SuccessResponse.of(insuranceService.getInsuranceInfo(insuranceId))
                .asHttp(HttpStatus.OK);
    }

    @Operation(summary = "전체 보험 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "전체 보험 정보 list 반환(보험이 없다면 빈 리스트 반환)")
    })
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<InsuranceDetailResponse>>> getInsuranceList() {
        return SuccessResponse.of(insuranceService.getInsuranceList())
                .asHttp(HttpStatus.OK);
    }

    @Operation(summary = "보험 약관 리스트 조회", description = "menu 2(보험 조회): 보험 약관 리스트 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 약관 리스트 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/terms")
    public ResponseEntity<SuccessResponse<List<TermInfoResponse>>> getInsuranceTerms(
            @PathVariable("id") Long insuranceId) {
        return SuccessResponse.of(termsService.getInsuranceTerms(insuranceId))
                .asHttp(HttpStatus.OK);
    }
}
