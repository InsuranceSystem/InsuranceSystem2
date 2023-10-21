package aplus.insurancesystem2.Insurance.repository;

import aplus.insurancesystem2.Insurance.domain.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, String> {
}
