package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.contract.dto.ContractDetailResponse;
import aplus.insurancesystem2.domain.contract.dto.ContractListResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import java.util.List;

public interface ContractService {

    ContractDetailResponse getContractDetail(String contractId);
    ContractListResponse getContractAll(String customerId);

    public List<Contract> getContracts(Customer customer);
    void createContract(InsuranceApplication insuranceApplication);
}
