package aplus.insurancesystem2.domain.contract.controller;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.contract.dto.response.ContractDetailResponse;
import aplus.insurancesystem2.domain.contract.dto.response.ContractListResponse;
import aplus.insurancesystem2.domain.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("{id}/detail")
    public ResponseEntity<SuccessResponse<ContractDetailResponse>> getContractDetail(@PathVariable String contractId) {
        return SuccessResponse.of(contractService.getContractDetail(contractId))
                .asHttp(HttpStatus.OK);
    }

    @GetMapping("{id}/all")
    public ResponseEntity<SuccessResponse<ContractListResponse>> getContractAll(@PathVariable String customerId) {
        return SuccessResponse.of(contractService.getAllContract(customerId))
                .asHttp(HttpStatus.OK);
    }
}
