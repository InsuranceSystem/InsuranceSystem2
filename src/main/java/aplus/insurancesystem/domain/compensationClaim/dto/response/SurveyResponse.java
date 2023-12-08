package aplus.insurancesystem.domain.compensationClaim.dto.response;

import aplus.insurancesystem.domain.compensationClaim.entity.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
