package aplus.insurancesystem2.domain.Insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplus.insurancesystem2.domain.Insurance.entity.InsuranceApplication;

public interface InsuranceApplicationRepository extends JpaRepository<InsuranceApplication, Long> {
}
