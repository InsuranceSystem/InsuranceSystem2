package aplus.insurancesystem2.domain.contract.service;

import aplus.insurancesystem2.domain.contract.domain.Contract;
import java.util.List;

public interface ContractService {
    boolean add(Contract contract);
    List<Contract> getall() throws Exception;
    List<Contract> getByCustomerId(String customerId);
    public List<String> getStatus(String customerId);
    List<Contract> getByInsuranceId(String insuranceID);
    List<String> getInsuranceIds(String customerId);
    String getPremium(String selectedCustomerId, String selectedInsuranceId);
    boolean updateCancellation(String customerId, String insuranceId);
    void setResurrection(String customerID);
    void setMaturity(String customerID);
}
