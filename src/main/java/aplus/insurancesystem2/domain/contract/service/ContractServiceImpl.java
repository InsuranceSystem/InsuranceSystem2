package aplus.insurancesystem2.domain.contract.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.contract.dto.ContractDetailResponse;
import aplus.insurancesystem2.domain.contract.dto.ContractInfo;
import aplus.insurancesystem2.domain.contract.dto.ContractListResponse;
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

    @Override
    public ContractDetailResponse getContractDetail(String contractId) {
        return contractRepository.findById(Long.parseLong(contractId))
                .map(ContractDetailResponse::of)
                .orElseThrow(ContractNotFoundException::new);
    }

    @Override
    public ContractListResponse getContractAll(String customerId) {
        return ContractListResponse.of(contractRepository.findByCustomerId(Long.parseLong(customerId))
                .stream()
                .map(ContractInfo::of)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean add(Contract newContract) {
        Contract savedContract = contractRepository.save(newContract);
        if (!isNull(savedContract)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Contract> getall() throws Exception {
        List<Contract> allContracts = contractRepository.findAll();
        if (allContracts.size() == 0) {
            throw new Exception("payment 데이터가 없습니다.");
        }
        return allContracts;
    }

    @Override
    public List<Contract> getByCustomerId(Long customerId) {
        return contractRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Contract> getByInsuranceId(Long insuranceId) {
        return contractRepository.findByInsuranceId(insuranceId);
    }

    @Override
    public List<Long> getInsuranceIds(Long customerId) {
        List<Long> insuranceIds = new ArrayList<>();
        for (Contract contract : contractRepository.findByCustomerId(customerId)) {
            insuranceIds.add(contract.getInsurance().getId());
        }
        return insuranceIds;
    }

    @Override
    public List<String> getStatus(Long customerId) {
        return contractRepository.findByCustomerId(customerId)
                .stream()
                .map(contract -> contract.getMaturity() + " " + contract.getCancellation()  )
                .collect(Collectors.toList());
    }

    @Override
    public String getPremium(Long customerId, Long insuranceId) {
        Optional<Contract> findContract = contractRepository.findByIds(customerId, insuranceId);
        return findContract.map(contract -> Integer.toString(contract.getPremium()))
                        .orElse("Contract not found");
    }

    @Override
    @Transactional
    public void setResurrection(Long customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerId(customerId);
        customerContracts.forEach(contract -> contract.setResurrection(false));
    }

    @Override
    @Transactional
    public void setMaturity(Long customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerId(customerId);
        customerContracts.forEach(contract -> contract.setMaturity(false));
    }

    @Override
    @Transactional
    public boolean updateCancellation(Long customerId, Long insuranceId) {
        Optional<Contract> findContract = contractRepository.findByIds(customerId, insuranceId);
        findContract.ifPresent(contract -> {
            contract.setCancellation(!contract.getCancellation());
        });
        return findContract.isPresent();
    }
}

