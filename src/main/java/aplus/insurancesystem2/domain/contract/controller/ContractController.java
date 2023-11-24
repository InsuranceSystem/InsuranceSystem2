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

    @PostMapping("/create")
    public boolean add(Contract contract) {
        return contractService.add(contract);
    }

    @GetMapping("/get/all")
    public List<Contract> get() throws Exception {
        return contractService.getall();
    }

    @GetMapping("/get/customer")
    public List<Contract> getByCustomerId(@RequestParam Long customerId) {
        return contractService.getByCustomerId(customerId);
    }

    @GetMapping("/get/insurance")
    public List<Contract> getByInsuranceId(@RequestParam Long insuranceId) {
        return contractService.getByInsuranceId(insuranceId);
    }

    @GetMapping("/insurance-id/get")
    public List<Long> getInsuranceIds(@RequestParam Long customerId) {
        return contractService.getInsuranceIds(customerId);
    }

    @GetMapping("/status/get")
    public List<String> getStatus(@RequestParam Long customerId) {
        return contractService.getStatus(customerId);
    }

    @GetMapping("/premium/get")
    public String getPremium(@RequestParam Long customerId, @RequestParam Long insuranceId) {
        return contractService.getPremium(customerId, insuranceId);
    }

    @PostMapping("/cancellation/update")
    public boolean updateCancellation(Long customerId, Long insuranceId) {
        return contractService.updateCancellation(customerId, insuranceId);
    }

    @PostMapping("/contract/resurrection/update")
    public void setResurrection(@RequestBody Long customerId) {
        contractService.setResurrection(customerId);
    }

    @PostMapping("/contract/maturity/update")
    public void setMaturity(@RequestBody Long customerId) {
        contractService.setMaturity(customerId);
    }
}
