package aplus.insurancesystem2.domain.contract.repository;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByCustomer(Customer customer);

    @Query("SELECT c FROM Contract c WHERE c.customer.id = :customerId")
    List<Contract> findByCustomerId(@Param("customerId") Long customerId);

}
