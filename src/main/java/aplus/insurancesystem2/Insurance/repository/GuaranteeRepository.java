package aplus.insurancesystem2.Insurance.repository;

import aplus.insurancesystem2.Insurance.domain.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuaranteeRepository extends JpaRepository<Guarantee, String> {
    List<Guarantee> findAllByInsuranceID(String insuranceID);
}
