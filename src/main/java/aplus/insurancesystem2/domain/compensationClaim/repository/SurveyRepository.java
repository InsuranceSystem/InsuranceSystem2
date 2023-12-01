package aplus.insurancesystem2.domain.compensationClaim.repository;

import aplus.insurancesystem2.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem2.domain.compensationClaim.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
