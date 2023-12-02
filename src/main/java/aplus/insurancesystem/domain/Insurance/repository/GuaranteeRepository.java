package aplus.insurancesystem.domain.Insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import aplus.insurancesystem.domain.Insurance.entity.Guarantee;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {

    @Modifying(flushAutomatically = true)
    @Query("delete from Guarantee g where g.insurance.id = :insuranceId")
    void deleteAllByInsuranceId(Long insuranceId);

}
