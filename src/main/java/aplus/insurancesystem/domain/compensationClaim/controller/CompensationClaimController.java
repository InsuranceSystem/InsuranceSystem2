package aplus.insurancesystem.domain.compensationClaim.controller;

import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.domain.Insurance.dto.request.CreateInsuranceApplicationRequest;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Operation(summary = "청구 서류 조회", description = "청구서류 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청구서류 반환",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)),
            @ApiResponse(
                    responseCode = "404",
                    description = "CC001: id에 해당하는 청구 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/document")
    public ResponseEntity<InputStreamResource>
    getSubscription(@Parameter(description = "청구 id", in = ParameterIn.PATH)
                    @PathVariable("id") Long ccid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .body(compensationClaimService.getDocument(ccid));
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
    @PostMapping(path = "/claim/{contractId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCompensationClaim(
            @Parameter(description = "계약 id", in = ParameterIn.PATH)
            @PathVariable("contractId") Long contractId,
            @ModelAttribute CreateCompensationClaimRequest request) {
        compensationClaimService.createCompensationClaim(contractId, request);
        return ResponseEntity.ok().build();
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
    @PostMapping(path = "/claim/car/{contractId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCarAccident(
            @Parameter(description = "계약 id", in = ParameterIn.PATH)
            @PathVariable("contractId") Long contractId,
            @ModelAttribute CreateCarAccidentRequest request) {
        compensationClaimService.createCarAccident(contractId,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
