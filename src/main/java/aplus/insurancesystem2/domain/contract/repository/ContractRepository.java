package aplus.insurancesystem2.domain.contract.repository;


import aplus.insurancesystem2.domain.contract.domain.Contract;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByCustomerID(String customerId);
    List<Contract> findByInsuranceID(String insuranceId);
}
