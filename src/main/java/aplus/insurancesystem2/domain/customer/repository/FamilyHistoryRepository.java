package aplus.insurancesystem2.domain.customer.repository;

import aplus.insurancesystem2.domain.customer.entity.FamilyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {
}
