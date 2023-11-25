package aplus.insurancesystem2.domain.Insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.Insurance.entity.Terms;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    @Query("select g.terms from Guarantee g where g.insurance = :insurance")
    List<Terms> findAllByInsurance(Insurance insurance);
}
