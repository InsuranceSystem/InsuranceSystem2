package aplus.insurancesystem.domain.compensationClaim.repository;

import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompensationClaimRepository extends JpaRepository<CompensationClaim, Long> {
    List<CompensationClaim> findAllByContractId(Long contractId);
}
