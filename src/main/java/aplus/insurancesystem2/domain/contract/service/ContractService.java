package aplus.insurancesystem2.domain.contract.service;

import java.util.List;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;

public interface ContractService {

    List<Contract> getContracts(Customer customer);

    void createContract(InsuranceApplication insuranceApplication);
}
