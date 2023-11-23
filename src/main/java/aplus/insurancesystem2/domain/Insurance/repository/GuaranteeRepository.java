package aplus.insurancesystem2.domain.Insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplus.insurancesystem2.domain.Insurance.domain.Guarantee;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {

}
