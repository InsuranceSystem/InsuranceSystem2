package aplus.insurancesystem2.domain.compensationClaim.dto.response;

import aplus.insurancesystem2.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem2.domain.compensationClaim.entity.CompensationClaim;
import aplus.insurancesystem2.domain.compensationClaim.entity.Survey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SurveyResponse {

    private final String managerName;
    private final String reportFilePath;
    private final Integer surveyFee;
    private final Integer decisionMoney;
    private final Boolean responsibility;
    private final String responsibilityReason;

    public static SurveyResponse of (Survey survey) {
        return new SurveyResponse(
                survey.getManagerName(),
                survey.getReportFilePath(),
                survey.getSurveyFee(),
                survey.getDecisionMoney(),
                survey.getResponsibility(),
                survey.getResponsibilityReason()
        );
    }
}
