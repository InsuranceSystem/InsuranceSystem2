package aplus.insurancesystem2.domain.Insurance.repository;

import aplus.insurancesystem2.domain.Insurance.domain.Guarantee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuaranteeRepository extends JpaRepository<Guarantee, String> {
    List<Guarantee> findAllByInsuranceID(String insuranceID);
}
