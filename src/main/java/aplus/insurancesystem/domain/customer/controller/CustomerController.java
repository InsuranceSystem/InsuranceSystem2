package aplus.insurancesystem.domain.customer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem.common.dto.SuccessResponse;
import aplus.insurancesystem.domain.customer.dto.request.CustomerUpdateRequest;
import aplus.insurancesystem.domain.customer.dto.request.JoinRequest;
import aplus.insurancesystem.domain.customer.dto.response.CustomerAllInfoResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerDetailResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerIdResponse;
import aplus.insurancesystem.domain.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem.domain.customer.dto.response.FamilyHistoryInfoResponse;
import aplus.insurancesystem.domain.customer.entity.TargetType;
import aplus.insurancesystem.domain.customer.service.CustomerService;
import aplus.insurancesystem.domain.customer.service.FamilyHistoryService;
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
    private final FamilyHistoryService familyHistoryService;

    @Operation(summary = "고객 정보 조회", description = "menu 6(고객 조회): 고객 정보 조회 API")
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

    @Operation(summary = "전체 고객 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 전체 반환(고객이 없다면 빈 리스트 반환)")
    })
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<CustomerAllInfoResponse>>> getCustomerList() {
        return SuccessResponse.of(
                customerService.getCustomerList()
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "고객 정보 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 전체 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/all")
    public ResponseEntity<SuccessResponse<CustomerAllInfoResponse>> getCustomerList(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId
    ) {
        return SuccessResponse.of(
                customerService.getCustomerAllInfo(customerId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "고객 정보 상세 조회", description = "menu 6(고객 조회): 고객 정보 상세조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/detail")
    public ResponseEntity<SuccessResponse<CustomerDetailResponse>> getCustomerDetail(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        return SuccessResponse.of(
                customerService.getCustomerDetail(customerId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "고객 정보 수정", description = "menu 6(고객 조회): 고객 정보 수정 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 수정 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId,
            @RequestBody CustomerUpdateRequest request) {
        customerService.updateCustomer(customerId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "고객 정보 삭제", description = "menu 6(고객 조회): 고객 정보 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 삭제 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
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

    @Operation(summary = "고객 가족력 정보 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 가족력 반환"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/families")
    public ResponseEntity<SuccessResponse<List<FamilyHistoryInfoResponse>>> getFamilyHistories(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        return SuccessResponse.of(
                familyHistoryService.getFamilyHistories(customerId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "계약 유지 대상자들 조회", description = "menu 6(고객 조회): 계약 유지 대상자들"
                                                        + "(만기계약대상자/미납대상자/부활대상자 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 가족력 반환")
    })
    @GetMapping("/contract-maintenance")
    public ResponseEntity<SuccessResponse<List<CustomerAllInfoResponse>>> getContractMaintenanceCustomers(
            @Parameter(description = "계약 유지 대상자 타입", in = ParameterIn.QUERY)
            @RequestParam TargetType targetType) {
        return SuccessResponse.of(
                customerService.getContractMaintenanceCustomers(targetType)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "회원가입", description = "회원가입 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "회원가입 완료")
    })
    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest request) {
        customerService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "admin 여부 확인")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "admin 여부 반환 (관리자: true, 고객: false)"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}/admin")
    public ResponseEntity<SuccessResponse<Boolean>> isAdmin(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId) {
        return SuccessResponse.of(
                customerService.isAdmin(customerId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "로그인 id 중복 확인 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "중복 여부 반환 (중복: true, 중복 아님: false)"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/validate-login-id")
    public ResponseEntity<SuccessResponse<Boolean>> validateLoginId(
            @Parameter(description = "login id", in = ParameterIn.QUERY)
            @RequestParam String loginId) {
        return SuccessResponse.of(
                customerService.validateLoginId(loginId)
        ).asHttp(HttpStatus.OK);
    }

    @Operation(summary = "고객 admin 지정/헤제 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "고객 admin 지정/헤제 완료"),
            @ApiResponse(
                    responseCode = "404",
                    description = "U001: id에 해당하는 고객을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PatchMapping("/{id}/admin")
    public ResponseEntity<Void> setAdmin(
            @Parameter(description = "고객 id", in = ParameterIn.PATH)
            @PathVariable("id") Long customerId,
            @Parameter(description = "admin 여부", in = ParameterIn.QUERY)
            @RequestParam boolean setAdmin) {
        customerService.setAdmin(customerId, setAdmin);
        return ResponseEntity.ok().build();
    }
}
