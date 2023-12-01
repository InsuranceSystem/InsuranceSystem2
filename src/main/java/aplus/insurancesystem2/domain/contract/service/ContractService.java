package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.contract.dto.response.ContractAllInfoResponse;
import aplus.insurancesystem2.domain.contract.dto.response.ContractDetailResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import java.util.List;

public interface ContractService {

    public List<Contract> getContracts(Customer customer);
    void createContract(InsuranceApplication insuranceApplication);
    ContractDetailResponse getContractDetail(Long contractId);
    List<ContractAllInfoResponse> getContractList(Long customerId);

    Integer getPremium(Long contractId);
}
