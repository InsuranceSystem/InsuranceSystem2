package aplus.insurancesystem.domain.Insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem.common.converter.GetCustomer;
import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.common.security.CustomerInfo;
import aplus.insurancesystem.domain.Insurance.dto.request.ApprovalInsuranceApplicationRequest;
import aplus.insurancesystem.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
import aplus.insurancesystem.domain.Insurance.dto.request.RejectInsuranceApplicationRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationDetailResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationInfoResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.InsuranceApplicationResultResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.MyInsuranceApplicationResponse;
import aplus.insurancesystem.domain.Insurance.dto.response.SubscriptionFilePathResponse;
import aplus.insurancesystem.domain.Insurance.service.InsuranceApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
            @Parameter(description = "보험 id", in = ParameterIn.PATH)
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

    @Operation(summary = "보험 가입 신청 내역 상세보기", description = "menu 10(보험 가입 신청 내역): 보험 가입 신청 내역 상세보기 API"
                                                           + "+ 보험료 산정 시 보여줄 고객, 보험, 가족력 정보 반환 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 가입 신청 내역 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "IA001: id에 해당하는 보험 가입 신청 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/detail")
    public ResponseEntity<SuccessResponse<InsuranceApplicationDetailResponse>>
        getInsuranceApplication(@Parameter(description = "보험 신청 id", in = ParameterIn.PATH)
                                @PathVariable("id") Long insuranceApplicationId) {
        return SuccessResponse.of(
                insuranceApplicationService.getInsuranceApplication(insuranceApplicationId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "청약서 조회", description = "menu 10(보험 가입 신청 내역): 청약서 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청약서 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "IA001: id에 해당하는 보험 가입 신청 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/subscription")
    public ResponseEntity<SuccessResponse<SubscriptionFilePathResponse>>
        getSubscription(@Parameter(description = "보험 신청 id", in = ParameterIn.PATH)
                        @PathVariable("id") Long insuranceApplicationId) {
        return SuccessResponse.of(
                insuranceApplicationService.getSubscription(insuranceApplicationId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "보험 가입 신청 승인" , description = "menu 10(보험 가입 신청 내역): 보험 가입 신청 승인 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 가입 신청 승인 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "IA001: id에 해당하는 보험 가입 신청 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/{id}/approval")
    public ResponseEntity<Void> approvalInsuranceApplication(
            @Parameter(description = "보험 신청 id", in = ParameterIn.PATH)
            @PathVariable("id") Long insuranceApplicationId,
            @RequestBody ApprovalInsuranceApplicationRequest request) {
        insuranceApplicationService.approvalInsuranceApplication(insuranceApplicationId,
                                                                 request.getReasonOfApproval());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "보험 가입 신청 거절", description = "menu 10(보험 가입 신청 내역): 보험 가입 신청 거절 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험 가입 신청 거절 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "IA001: id에 해당하는 보험 가입 신청 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/{id}/rejection")
    public ResponseEntity<Void> rejectionInsuranceApplication(
            @Parameter(description = "보험 신청 id", in = ParameterIn.PATH)
            @PathVariable("id") Long insuranceApplicationId,
            @RequestBody RejectInsuranceApplicationRequest request) {
        insuranceApplicationService.rejectionInsuranceApplication(insuranceApplicationId,
                                                                  request.getReasonOfRejection());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내 보험 신청 내역 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내 보험 가입 신청 내역 리스트 반환(신청 내역이 없다면 빈 리스트 반환)")
    })
    @GetMapping("/my")
    public ResponseEntity<SuccessResponse<List<MyInsuranceApplicationResponse>>>
        getMyInsuranceApplicationList(@GetCustomer CustomerInfo customerInfo) {
        return SuccessResponse.of(
                insuranceApplicationService.getMyInsuranceApplicationList(customerInfo.getCustomerId())
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "보험 신청서 결과 조회", description = "로그인한 고객이 보험 신청서 결과를 조회하는 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "신청서 결과 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "IA001: id에 해당하는 보험 가입 신청 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/result")
    public ResponseEntity<SuccessResponse<InsuranceApplicationResultResponse>>
        getInsuranceApplicationInfo(@Parameter(description = "보험 신청 id", in = ParameterIn.PATH)
                                    @PathVariable("id") Long insuranceApplicationId) {
        return SuccessResponse.of(
                insuranceApplicationService.getInsuranceApplicationResult(insuranceApplicationId)
        ).asHttp(HttpStatus.OK);
    }
}
