package aplus.insurancesystem2.domain.contract.repository;


import aplus.insurancesystem2.domain.contract.domain.Contract;
import aplus.insurancesystem2.domain.contract.domain.ContractId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, ContractId> {

    List<Contract> findByCustomerID(String customerId);

    List<Contract> findByInsuranceID(String insuranceId);
}
