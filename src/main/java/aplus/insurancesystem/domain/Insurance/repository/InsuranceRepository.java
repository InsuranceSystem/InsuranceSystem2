package aplus.insurancesystem.domain.Insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplus.insurancesystem.domain.Insurance.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query("select i from Insurance i join fetch i.guaranteeList")
    List<Insurance> findAllWithGuarantees();
}
