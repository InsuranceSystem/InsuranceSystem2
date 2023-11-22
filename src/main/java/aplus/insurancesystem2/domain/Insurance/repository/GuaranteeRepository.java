package aplus.insurancesystem2.domain.Insurance.repository;

import aplus.insurancesystem2.domain.Insurance.domain.Guarantee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {
    List<Guarantee> findAllByInsuranceID(Long insuranceID);
}
