package aplus.insurancesystem2.domain.contract.service;

import static java.util.Objects.isNull;

import aplus.insurancesystem2.domain.contract.domain.Contract;
import aplus.insurancesystem2.domain.contract.domain.ContractId;
import aplus.insurancesystem2.domain.contract.repository.ContractRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public boolean add(Contract contract) {
        Contract savedContract = contractRepository.save(contract);
        if (!isNull(savedContract)) {
            return true;
        }
        return false;
    }

    public List<Contract> retrieve() throws Exception {
        List<Contract> allContract = contractRepository.findAll();
        if (allContract.size() == 0) {
            throw new Exception("payment 데이터가 없습니다.");
        }
        return allContract;
    }

    public List<Contract> retrieveCustomerContract(String customerId) {
        return contractRepository.findByCustomerID(customerId);
    }

    public List<String> retrieveCustomerContractStatus(String customerId) {
        List<String> contractStatus = new ArrayList<>();
        List<Contract> customerContracts = contractRepository.findByCustomerID(customerId);
        for (Contract contract : customerContracts) {
            contractStatus.add(contract.isMaturity() + " " + contract.isCancellation());
        }
        return contractStatus;
    }

    // 확인 필요
    public List<Contract> getContractByInsuranceID(String insuranceId) {
        return contractRepository.findByInsuranceID(insuranceId);
    }

    @Transactional
    public boolean updateCancellation(String customerId, String insuranceId) {
        Optional<Contract> findContract = contractRepository.findById(new ContractId(customerId, insuranceId));
        Contract contract = findContract.get();
        if (!isNull(contract)) {
            contract.changeCancellation();
            return true;
        }
        return false;
    }

    @Transactional
    public void setResurrectFromCustomer(String customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerID(customerId);
        for (Contract contract : customerContracts) {
            contract.changeResurrection(false);
        }
    }

    @Transactional
    public void setMaturityFromCustomer(String customerId) {
        List<Contract> customerContracts = contractRepository.findByCustomerID(customerId);
        for (Contract contract : customerContracts) {
            contract.changeMaturity(false);
        }
    }

    public List<String> getInsuranceIdFromCustomerId(String customerId) {
        List<String> insuranceIdFromCustomerId = new ArrayList<>();
        List<Contract> customerContracts = contractRepository.findByCustomerID(customerId);
        for (Contract contract : customerContracts) {
            insuranceIdFromCustomerId.add(contract.getInsuranceID());
        }
        return insuranceIdFromCustomerId;
    }

    // 확인 필요
    public String retrievePremiumById(String customerId, String insuranceId) {
        Optional<Contract> findContract = contractRepository.findById(new ContractId(customerId, insuranceId));
        Contract contract = findContract.get();
        return Integer.toString(contract.getPremium());
    }
}

