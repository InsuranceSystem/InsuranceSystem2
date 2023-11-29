package aplus.insurancesystem2.domain.contract.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.contract.dto.ContractInfoResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.contract.exception.ContractNotFoundException;
import aplus.insurancesystem2.domain.contract.repository.ContractRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractInfoResponse getContractInfo(String contractId) {
        return contractRepository.findById(Long.parseLong(contractId))
                .map(ContractInfoResponse::of)
                .orElseThrow(ContractNotFoundException::new);
    }

    public boolean add(Contract newContract) {
        Contract savedContract = contractRepository.save(newContract);
        if (!isNull(savedContract)) {
            return true;
        }
        return false;
    }

    public List<Contract> getall() throws Exception {
        List<Contract> allContracts = contractRepository.findAll();
        if (allContracts.size() == 0) {
            throw new Exception("payment 데이터가 없습니다.");
        }
        return allContracts;
    }

    public List<Contract> getByCustomerId(Long customerId) {
        return contractRepository.findByCustomerId(customerId);
    }

    public List<Contract> getByInsuranceId(Long insuranceId) {
        return contractRepository.findByInsuranceId(insuranceId);
    }

    public List<Long> getInsuranceIds(Long customerId) {
        List<Long> insuranceIds = new ArrayList<>();
        for (Contract contract : contractRepository.findByCustomerId(customerId)) {
            insuranceIds.add(contract.getInsurance().getId());
        }
        return insuranceIds;
    }

    public List<String> getStatus(Long customerId) {
        return contractRepository.findByCustomerId(customerId)
                .stream()
                .map(contract -> contract.getMaturity() + " " + contract.getCancellation()  )
                .collect(Collectors.toList());
    }

    public String getPremium(Long customerId, Long insuranceId) {
        Optional<Contract> findContract = contractRepository.findByIds(customerId, insuranceId);
        return findContract.map(contract -> Integer.toString(contract.getPremium()))
                        .orElse("Contract not found");
    }

    @Transactional
    public void setResurrection(Long customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerId(customerId);
        customerContracts.forEach(contract -> contract.setResurrection(false));
    }

    @Transactional
    public void setMaturity(Long customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerId(customerId);
        customerContracts.forEach(contract -> contract.setMaturity(false));
    }

    @Transactional
    public boolean updateCancellation(Long customerId, Long insuranceId) {
        Optional<Contract> findContract = contractRepository.findByIds(customerId, insuranceId);
        findContract.ifPresent(contract -> {
            contract.setCancellation(!contract.getCancellation());
        });
        return findContract.isPresent();
    }
}

