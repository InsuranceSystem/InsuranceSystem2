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
import aplus.insurancesystem.domain.compensationClaim.service.SurveyService;
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
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    @Operation(summary = "손해사정 내역 상세 조회", description = "청구ID로 손해사정 내역 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청구ID로 손해사정 내역 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "S001: id에 해당하는 손해사정 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{ccid}")
    public ResponseEntity<SuccessResponse<SurveyResponse>> getSurvey(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                surveyService.getSurvey(CCID)
        ).asHttp(HttpStatus.OK);
    }
    @Operation(summary = "손해사정", description = "손해사정 생성 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "손해사정 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "CC001: id에 해당하는 청구내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true))
            ),

    })
    @PostMapping(path = "/{ccid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createSurvey(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long ccid,
            @ModelAttribute CreateSurveyRequest request) {
        surveyService.createSurvey(ccid, request);
        return ResponseEntity.ok().build();
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
    @PutMapping(path = "/{ccid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateSurvey(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long ccid,
            @ModelAttribute UpdateSurveyRequest request) {
        surveyService.updateSurvey(ccid, request);
        return ResponseEntity.ok().build();
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
    @GetMapping("/{ccid}/report")
    public ResponseEntity<InputStreamResource> getReport(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long ccid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .body(surveyService.getReport(ccid));
    }

}
