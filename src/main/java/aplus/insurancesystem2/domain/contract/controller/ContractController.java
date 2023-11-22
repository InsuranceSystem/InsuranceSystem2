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
        return contractService.getall();
    }

    @GetMapping("/contract/get")
    public List<Contract> getByCustomerId(@RequestParam String customerId) {
        return contractService.getByCustomerId(customerId);
    }

    @GetMapping("/contract/get")
    public List<Contract> getByInsuranceId(@RequestParam String insuranceId) {
        return contractService.getByInsuranceId(insuranceId);
    }

    @GetMapping("/contract/insurance-id/get")
    public List<String> getInsuranceIds(@RequestParam String customerId) {
        return contractService.getInsuranceIds(customerId);
    }

    @GetMapping("/contract/status/get")
    public List<String> getStatus(@RequestParam String customerId) {
        return contractService.getStatus(customerId);
    }

    @GetMapping("/contract/premium/get")
    public String getPremium(@RequestParam String customerId, @RequestParam String insuranceId) {
        return contractService.getPremium(customerId, insuranceId);
    }

    @PostMapping("/contract/cancellation/update")
    public boolean updateCancellation(String customerId, String insuranceId) {
        return contractService.updateCancellation(customerId, insuranceId);
    }

    @PostMapping("/contract/resurrection/update")
    public void setResurrection(@RequestBody String customerId) {
        contractService.setResurrection(customerId);
    }

    @PostMapping("/contract/maturity/update")
    public void setMaturity(@RequestBody String customerId) {
        contractService.setMaturity(customerId);
    }
}
