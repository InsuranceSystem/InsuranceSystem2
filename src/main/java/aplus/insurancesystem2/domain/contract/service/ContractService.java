package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.contract.domain.Contract;
import java.util.List;

public interface ContractService {
    boolean add(Contract contract);
    List<Contract> retrieve() throws Exception;
    boolean updateCancellation(String customerId, String insuranceId);
    void setResurrectFromCustomer(String customerID);
    void setMaturityFromCustomer(String customerID);
    List<Contract> retrieveCustomerContract(String customerId);
    List<String> getInsuranceIdFromCustomerId(String customerId);
    List<Contract> getContractByInsuranceID(String insuranceID);
    String retrievePremiumById(String selectedCustomerId, String selectedInsuranceId);
}
