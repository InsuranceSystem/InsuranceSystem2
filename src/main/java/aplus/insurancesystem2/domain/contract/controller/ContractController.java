package aplus.insurancesystem2.domain.contract.controller;

import aplus.insurancesystem2.domain.contract.domain.Contract;
import aplus.insurancesystem2.domain.contract.service.ContractService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/contract/create")
    public boolean add(Contract contract) {
        return contractService.add(contract);
    }

    @GetMapping("/contract/get/all")
    public List<Contract> get() throws Exception {
        return contractService.retrieve();
    }

    @GetMapping("/contract/get")
    public List<Contract> getByCustomerId(@RequestParam String customerId) {
        return contractService.retrieveCustomerContract(customerId);
    }

    @GetMapping("/contract/status/get")
    public List<String> getStatus(@RequestParam String customerId) {
        return contractService.retrieveCustomerContractStatus(customerId);
    }

    @GetMapping("/contract/get")
    public List<Contract> getByInsuranceId(@RequestParam String insuranceId) {
        return contractService.getContractByInsuranceID(insuranceId);
    }

    @GetMapping("/contract/insurance-id/get")
    public List<String> getInsuranceId(@RequestParam String customerId) {
        return contractService.getInsuranceIdFromCustomerId(customerId);
    }

    @GetMapping("/contract/premium/get")
    public String retrievePremiumById(@RequestParam String customerId, @RequestParam String insuranceId) {
        return contractService.retrievePremiumById(customerId, insuranceId);
    }

    @PostMapping("/contract/cancellation/update")
    public boolean updateCancellation(String customerId, String insuranceId) {
        return contractService.updateCancellation(customerId, insuranceId);
    }

    @PostMapping("/contract/resurrection/update")
    public void preventResurrection(@RequestBody String customerId) {
        contractService.setResurrectFromCustomer(customerId);
    }

    @PostMapping("/contract/maturity/update")
    public void setMaturity(@RequestBody String customerId) {
        contractService.setMaturityFromCustomer(customerId);
    }
}
