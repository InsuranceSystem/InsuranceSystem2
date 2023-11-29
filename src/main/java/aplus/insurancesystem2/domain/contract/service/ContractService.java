package aplus.insurancesystem2.domain.contract.service;

<<<<<<< HEAD
import aplus.insurancesystem2.domain.contract.dto.ContractDetailResponse;
import aplus.insurancesystem2.domain.contract.dto.ContractListResponse;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import java.util.List;

public interface ContractService {

    ContractDetailResponse getContractDetail(String contractId);
    ContractListResponse getContractAll(String customerId);
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
=======
import java.util.List;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;

public interface ContractService {

    List<Contract> getContracts(Customer customer);

    void createContract(InsuranceApplication insuranceApplication);
>>>>>>> 88576b260b94243ffa6788228039aef3bb2ff71f
}
