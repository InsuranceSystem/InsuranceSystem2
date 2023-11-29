package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.contract.dto.ContractInfoResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import java.util.List;

public interface ContractService {

    ContractInfoResponse getContractInfo(String contractId);
    boolean add(Contract contract);
    List<Contract> getall() throws Exception;
    List<Contract> getByCustomerId(Long customerId);
    public List<String> getStatus(Long customerId);
    List<Contract> getByInsuranceId(Long insuranceID);
    List<Long> getInsuranceIds(Long customerId);
    String getPremium(Long selectedCustomerId, Long selectedInsuranceId);
    boolean updateCancellation(Long customerId, Long insuranceId);
    void setResurrection(Long customerID);
    void setMaturity(Long customerID);
}
