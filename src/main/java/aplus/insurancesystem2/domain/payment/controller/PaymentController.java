package aplus.insurancesystem2.domain.payment.controller;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.payment.dto.PaymentInfoResponse;
import aplus.insurancesystem2.domain.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "납입 정보 조회", description = "menu 11(납입 관리 메뉴): 납입 정보 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "납입 정보 list 반환(납입이 없다면 빈 리스트 반환)"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<List<PaymentInfoResponse>>> getPaymentList(
            @Parameter(description = "계약 id", in = ParameterIn.PATH)
            @PathVariable("id") Long contractId) {
        return SuccessResponse.of(paymentService.getPaymentList(contractId))
                .asHttp(HttpStatus.OK);
    }
}
