package aplus.insurancesystem2.domain.Insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.Insurance.dto.request.DesignInsuranceRequest;
import aplus.insurancesystem2.domain.Insurance.dto.request.UpdateInsuranceRequest;
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
        return SuccessResponse.of(
                insuranceService.getInsuranceInfo(insuranceId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "전체 보험 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "전체 보험 정보 list 반환(보험이 없다면 빈 리스트 반환)")
    })
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<InsuranceDetailResponse>>> getInsuranceList() {
        return SuccessResponse.of(
                insuranceService.getInsuranceList()
        ).asHttp(HttpStatus.OK);
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
        return SuccessResponse.of(
                termsService.getInsuranceTerms(insuranceId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "새 보험 설계 API", description = "menu 8(보험 설계): 보험 설계 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "보험 설계 성공"),
            @ApiResponse(
                    responseCode = "404",
                    description = "T001: id에 해당하는 보험 약관을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/design")
    public ResponseEntity<Void> designInsurance(@RequestBody DesignInsuranceRequest request) {
        insuranceService.designInsurance(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "보험 수정", description = "menu 8(보험 설계): 보험 수정 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 수정 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInsurance(
            @PathVariable("id") Long insuranceId,
            @RequestBody UpdateInsuranceRequest request) {
        insuranceService.updateInsurance(insuranceId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "보험 삭제", description = "menu 8(보험 설계): 보험 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 수정 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable("id") Long insuranceId) {
        insuranceService.deleteInsurance(insuranceId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "보험 등록", description = "menu 8(보험 설계): 보험 등록 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 등록 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/{id}/register")
    public ResponseEntity<Void> registerInsurance(@PathVariable("id") Long insuranceId) {
        insuranceService.registerInsurance(insuranceId);
        return ResponseEntity.ok().build();
    }


}
