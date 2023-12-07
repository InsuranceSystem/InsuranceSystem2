package aplus.insurancesystem.domain.Insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplus.insurancesystem.domain.Insurance.entity.insurance.Insurance;
import aplus.insurancesystem.domain.Insurance.entity.Terms;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    @Query("select g.terms from Guarantee g where g.insurance = :insurance")
    List<Terms> findAllByInsurance(Insurance insurance);
}
