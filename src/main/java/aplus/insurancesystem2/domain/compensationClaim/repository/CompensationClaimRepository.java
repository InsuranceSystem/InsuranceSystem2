package aplus.insurancesystem2.domain.compensationClaim.repository;

import aplus.insurancesystem2.domain.compensationClaim.entity.CompensationClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CompensationClaimRepository extends JpaRepository<CompensationClaim, Long> {
    List<CompensationClaim> findAllByCustomerId(Long customerId);
}
