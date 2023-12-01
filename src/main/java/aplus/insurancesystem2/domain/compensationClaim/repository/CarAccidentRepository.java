package aplus.insurancesystem2.domain.compensationClaim.repository;

import aplus.insurancesystem2.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem2.domain.compensationClaim.entity.CompensationClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarAccidentRepository extends JpaRepository<CarAccident, Long> {


}
