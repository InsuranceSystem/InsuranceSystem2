package aplus.insurancesystem2.domain.Insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem2.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem2.domain.Insurance.service.InsuranceApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/insurance-applications")
@RequiredArgsConstructor
public class InsuranceApplicationController {

    private final InsuranceApplicationService insuranceApplicationService;

    @Operation(summary = "보험 신청", description = "menu 8(보험 설계): 보험 신청 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 신청 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다. \n"
                                  + "U001: id에 해당하는 유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/{id}")
    public ResponseEntity<Void> applyInsurance(
            @PathVariable("id") Long insuranceId,
            @RequestBody CreateInsuranceApplicationRequest request) {
        insuranceApplicationService.applyInsurance(insuranceId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "보험 가입 신청 내역 리스트 조회", description = "menu 10(보험 가입 신청 내역): 보험 가입 신청 내역 리스트 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 가입 신청 내역 리스트 반환(신청 내역이 없다면 빈 리스트 반환)")
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<List<InsuranceApplicationInfoResponse>>>
        getInsuranceApplicationList() {
        return SuccessResponse.of(
                insuranceApplicationService.getInsuranceApplicationList()
        ).asHttp(HttpStatus.OK);
    }
}
