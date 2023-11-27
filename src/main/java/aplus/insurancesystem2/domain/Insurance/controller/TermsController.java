package aplus.insurancesystem2.domain.Insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.Insurance.dto.response.TermInfoResponse;
import aplus.insurancesystem2.domain.Insurance.service.TermsService;
import io.swagger.v3.oas.annotations.Operation;
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


}
