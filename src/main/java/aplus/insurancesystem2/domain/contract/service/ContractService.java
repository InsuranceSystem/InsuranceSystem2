package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.contract.domain.Contract;
import java.util.List;

public interface ContractService {
    boolean add(Contract contract);
    List<Contract> retrieve() throws Exception;
    List<Contract> retrieveCustomerContract(String customerId);
    public List<String> retrieveCustomerContractStatus(String customerId);
    List<Contract> getContractByInsuranceID(String insuranceID);
    List<String> getInsuranceIdFromCustomerId(String customerId);
    String retrievePremiumById(String selectedCustomerId, String selectedInsuranceId);
    boolean updateCancellation(String customerId, String insuranceId);
    void setResurrectFromCustomer(String customerID);
    void setMaturityFromCustomer(String customerID);
}
