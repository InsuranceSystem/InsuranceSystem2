package aplus.insurancesystem.domain.compensationClaim.controller;

import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.domain.compensationClaim.dto.request.CreateCarAccidentRequest;
import aplus.insurancesystem.domain.compensationClaim.dto.response.CarAccidentResponse;
import aplus.insurancesystem.domain.compensationClaim.service.CarAccidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car-accident")
@RequiredArgsConstructor
public class CarAccidentController {
    private final CarAccidentService carAccidentService;

    @Operation(summary = "개별 보상금 청구 및 사고 접수 내역 상세 조회",
            description = "청구ID로 보상금 청구 및사고 접수 내역 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "청구ID로 보상금 청구 및 사고 접수 내역 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "CA001: id에 해당하는 사고 접수 내역을 찾을 수 없습니다.\n"
                        + "CC001: id에 해당하는 보상금 청구 내역을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/detail/{ccid}")
    public ResponseEntity<SuccessResponse<CarAccidentResponse>> getCarAccidentDetail(
            @Parameter(description = "청구 id", in = ParameterIn.PATH)
            @PathVariable("ccid") Long CCID) {
        return SuccessResponse.of(
                carAccidentService.getCarAccidentDetail(CCID)
        ).asHttp(HttpStatus.OK);
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
    @PostMapping(path = "/claim/{contractId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCarAccident(
            @Parameter(description = "계약 id", in = ParameterIn.PATH)
            @PathVariable("contractId") Long contractId,
            @ModelAttribute CreateCarAccidentRequest request) {
        carAccidentService.createCarAccident(contractId,request);
        return ResponseEntity.ok().build();
    }
}
