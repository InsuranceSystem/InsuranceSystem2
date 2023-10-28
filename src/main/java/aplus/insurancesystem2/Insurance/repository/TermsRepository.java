package aplus.insurancesystem2.Insurance.repository;

import aplus.insurancesystem2.Insurance.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, String> {
}
