package aplus.insurancesystem.domain.Insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.domain.Insurance.dto.request.CreateTermsRequest;
import aplus.insurancesystem.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem.domain.Insurance.service.TermsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/terms")
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    @Operation(summary = "약관 리스트 조회", description = "menu 8(보험 설계): 약관 리스트 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "약관 리스트 반환")
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<List<TermInfoResponse>>> getTermList() {
        return SuccessResponse.of(
                termsService.getTermsList()
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "새 약관 등록 API", description = "menu 8(보험 설계): 새 약관 등록 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "약관 등록 성공"),
            @ApiResponse(
                    responseCode = "404",
                    description = "T001: id에 해당하는 약관을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/create")
    public ResponseEntity<Void> createTerms(@RequestBody CreateTermsRequest request) {
        termsService.createTerms(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
