package aplus.insurancesystem.domain.compensationClaim.repository;

import aplus.insurancesystem.domain.compensationClaim.entity.CarAccident;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarAccidentRepository extends JpaRepository<CarAccident, Long> {


}
