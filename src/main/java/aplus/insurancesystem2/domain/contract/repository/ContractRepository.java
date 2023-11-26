package aplus.insurancesystem2.domain.contract.repository;


import aplus.insurancesystem2.domain.contract.entity.Contract;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE c.customer.id = :customerId")
    List<Contract> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT c FROM Contract c WHERE c.insurance.id = :insuranceId")
    List<Contract> findByInsuranceId(@Param("insuranceId") Long insuranceId);

    @Query("SELECT c FROM Contract c WHERE c.customer.id = :customerId AND c.insurance.id = :insuranceId")
    Optional<Contract> findByIds(@Param("customerId") Long customerId, @Param("insuranceId") Long insuranceId);
}
