package aplus.insurancesystem.domain.contract.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractAllInfoResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractCancelContentResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractCancelResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractDetailResponse;
import aplus.insurancesystem.domain.contract.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @Operation(summary = "계약 정보 상세 조회", description = "계약 조회: 계약 정보 상세 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "계약 정보 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "C001: id에 해당하는 계약을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/detail")
    public ResponseEntity<SuccessResponse<ContractDetailResponse>> getContractDetail(
            @Parameter(description = "계약 id", in = ParameterIn.PATH)
            @PathVariable("id") Long contractId) {
        return SuccessResponse.of(contractService.getContractDetail(contractId))
                .asHttp(HttpStatus.OK);
    }

    @Operation(summary = "계약 정보 전체 조회", description = "계약 조회: 계약 정보 전체 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "계약 정보 list 반환(계약이 없다면 빈 리스트 반환)")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<List<ContractAllInfoResponse>>> getContractList(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        return SuccessResponse.of(contractService.getContractList(customerId))
                .asHttp(HttpStatus.OK);
    }

    @Operation(summary = "계약 해지 정보 조회", description = "계약 해지: 계약 중도/만기 해지 정보 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "해지 시 예상 정보 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "C001: id에 해당하는 계약을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/cancel-content")
    public ResponseEntity<SuccessResponse<ContractCancelContentResponse>> getCancelContent(
        @Parameter(description = "계약 id", in = ParameterIn.PATH)
        @PathVariable("id") Long contractId) {
        return SuccessResponse.of(contractService.getCancelContent(contractId))
                .asHttp(HttpStatus.OK);
    }

    @Operation(summary = "계약 해지", description = "계약 해지: 계약 중도/만기 해지 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "해지 시 필요 정보 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "C001: id에 해당하는 계약을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<SuccessResponse<ContractCancelResponse>> cancel(
        @Parameter(description = "계약 id", in = ParameterIn.PATH)
        @PathVariable("id") Long contractId) {
        return SuccessResponse.of(contractService.cancel(contractId))
                .asHttp(HttpStatus.OK);
    }
}
