package aplus.insurancesystem.domain.compensationClaim.controller;

import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCompensationClaimRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.request.UpdateSurveyRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CompensationClaimResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.response.SurveyResponse;
import aplus.insurancesystem.domain.compensationClaim.service.CompensationClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compensation-claim")
@RequiredArgsConstructor
public class CompensationClaimController {
    private final CompensationClaimService compensationClaimService;

    @Operation(summary = "모든 청구 내역(리스트) 조회", description = "(관리자용) 모든 청구 내역(리스트) 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "모든 청구 내역(리스트) 반환"),
    })
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<CompensationClaimResponse>>> getAllCompensationClaim() {
        return SuccessResponse.of(
                compensationClaimService.getAllCompensationClaim()
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "고객별 청구 내역(리스트) 조회", description = "고객ID로 청구 내역(리스트) 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객ID로 청구 내역(리스트) 반환"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<List<CompensationClaimResponse>>> getCompensationClaim(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        return SuccessResponse.of(
                compensationClaimService.getCompensationClaim(customerId)
        ).asHttp(HttpStatus.OK);
    }
    @Operation(summary = "개별 청구 내역 상세 조회", description = "청구ID로 청구 내역 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청구ID로 청구 내역 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "CC001: id에 해당하는 청구 내역을 찾을 수 없습니다.",
//                    responseCode = ErrorCode.COMPENSATION_CLAIM_NOT_FOUND.getStatus(),
//                    description = ErrorCode.COMPENSATION_CLAIM_NOT_FOUND.getCode() + ": " + ErrorCode.COMPENSATION_CLIAM_NOT_FOUND.getMessage(),
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/detail/{ccid}")
    public ResponseEntity<SuccessResponse<CompensationClaimResponse>> getCompensationClaimDetail(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                compensationClaimService.getCompensationClaimDetail(CCID)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "개별 사고 접수 내역 상세 조회", description = "청구ID로 사고 접수 내역 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청구ID로 사고 접수 내역 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "CA001: id에 해당하는 사고 접수 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/detail/car/{ccid}")
    public ResponseEntity<SuccessResponse<CarAccidentResponse>> getCarAccidentDetail(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                compensationClaimService.getCarAccidentDetail(CCID)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "보상금 청구", description = "보상금 청구 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보상금 청구 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다. \n"
                            + "U001: id에 해당하는 유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/claim")
    public ResponseEntity<Void> createCompensationClaim(@RequestBody CreateCompensationClaimRequest request) {
        compensationClaimService.createCompensationClaim(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "보상금 청구 및 사고 접수", description = "보상금 청구 및 사고 접수 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보상금 청구 및 사고 접수 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "I001: id에 해당하는 보험을 찾을 수 없습니다. \n"
                            + "U001: id에 해당하는 유저를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/claim/car")
    public ResponseEntity<Void> createCarAccident(@RequestBody CreateCarAccidentRequest request) {
        compensationClaimService.createCarAccident(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //손해사정
    @Operation(summary = "손해사정 내역 상세 조회", description = "청구ID로 손해사정 내역 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청구ID로 손해사정 내역 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "S001: id에 해당하는 손해사정 내역을 찾을 수 없습니다.",
                    //compensationClaim Exception도 던져야하나? (serviceImpl)
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/survey/{ccid}")
    public ResponseEntity<SuccessResponse<SurveyResponse>> getSurvey(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                compensationClaimService.getSurvey(CCID)
        ).asHttp(HttpStatus.OK);
    }
    @Operation(summary = "손해사정", description = "손해사정 생성 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "손해사정 완료"),
    })
    @PostMapping("/survey")
    public ResponseEntity<Void> createSurvey(@RequestBody CreateSurveyRequest request) {
        compensationClaimService.createSurvey(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "손해사정 수정", description = "손해사정 수정 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "손해사정 내역 수정 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "S001: id에 해당하는 손해사정 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/survey/{ccid}")
    public ResponseEntity<Void> updateSurvey(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long ccid, @RequestBody UpdateSurveyRequest request) {
        compensationClaimService.updateSurvey(ccid, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
