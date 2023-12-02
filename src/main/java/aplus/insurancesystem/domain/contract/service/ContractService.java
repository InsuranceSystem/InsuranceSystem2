package aplus.insurancesystem.domain.contract.service;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import aplus.insurancesystem.domain.contract.dto.response.ContractAllInfoResponse;
import aplus.insurancesystem.domain.contract.dto.response.ContractDetailResponse;
import aplus.insurancesystem.domain.contract.entity.Contract;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import java.util.List;

public interface ContractService {

    public List<Contract> getContracts(Customer customer);
    void createContract(InsuranceApplication insuranceApplication);
    ContractDetailResponse getContractDetail(Long contractId);
    List<ContractAllInfoResponse> getContractList(Long customerId);

    Integer getPremium(Long contractId);
}
