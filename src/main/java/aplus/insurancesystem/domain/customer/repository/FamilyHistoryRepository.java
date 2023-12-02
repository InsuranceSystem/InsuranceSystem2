package aplus.insurancesystem.domain.customer.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aplus.insurancesystem.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;

public interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {
    List<FamilyHistory> findAllByCustomer(Customer customer);

}
