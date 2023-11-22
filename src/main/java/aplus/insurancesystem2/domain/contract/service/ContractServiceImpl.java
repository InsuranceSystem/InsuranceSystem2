package aplus.insurancesystem2.domain.contract.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.contract.domain.Contract;
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

    public List<Contract> getByCustomerId(String customerId) {
        return contractRepository.findByCustomerId(customerId);
    }

    public List<Contract> getByInsuranceId(String insuranceId) {
        return contractRepository.findByInsuranceId(insuranceId);
    }

    public List<String> getInsuranceIds(String customerId) {
        List<String> insuranceIds = new ArrayList<>();
        for (Contract contract : contractRepository.findByCustomerId(customerId)) {
            insuranceIds.add(contract.getInsurance().getId());
        }
        return insuranceIds;
    }

    public List<String> getStatus(String customerId) {
        return contractRepository.findByCustomerId(customerId)
                .stream()
                .map(contract -> contract.isMaturity() + " " + contract.isCancellation())
                .collect(Collectors.toList());
    }

    public String getPremium(String customerId, String insuranceId) {
        Optional<Contract> findContract = contractRepository.findByIds(customerId, insuranceId);
        return findContract.map(contract -> Integer.toString(contract.getPremium()))
                        .orElse("Contract not found");
    }

    @Transactional
    public void setResurrection(String customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerId(customerId);
        customerContracts.forEach(contract -> contract.changeResurrection(false));
    }

    @Transactional
    public void setMaturity(String customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerId(customerId);
        customerContracts.forEach(contract -> contract.changeMaturity(false));
    }

    @Transactional
    public boolean updateCancellation(String customerId, String insuranceId) {
        Optional<Contract> findContract = contractRepository.findByIds(customerId, insuranceId);
        findContract.ifPresent(contract -> {
            contract.changeCancellation();
        });
        return findContract.isPresent();
    }
}

