package aplus.insurancesystem2.domain.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem2.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem2.domain.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "고객 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<CustomerInfoResponse>> getCustomer(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        return SuccessResponse.of(
                customerService.getCustomerInfo(customerId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "보험자 정보 확인", description = "menu 4(내 보험 확인): 보험자 정보 확인용 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "보험자 정보 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: customerName과 pnumber에 해당하는 고객을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<CustomerIdResponse>> validateCustomer(
            @Parameter(description = "보험자 이름", in = ParameterIn.QUERY) @RequestParam String customerName,
            @Parameter(description = "보험자 전화번호", in = ParameterIn.QUERY) @RequestParam String pnumber
    ) {
        return SuccessResponse.of(
                customerService.validateCustomer(customerName, pnumber)
        ).asHttp(HttpStatus.OK);
    }

}
