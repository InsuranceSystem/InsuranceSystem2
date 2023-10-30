package aplus.insurancesystem2.domain.Insurance.repository;

import aplus.insurancesystem2.domain.Insurance.domain.Terms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, String> {
}
